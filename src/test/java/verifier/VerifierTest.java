package verifier;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class VerifierTest {
    @Test
    void shouldGenerateVerifierStep() {
        var dut = Verifier.forObject("i am a test object hehe");
        assertThat(dut).isNotNull().isEqualTo(new Verifier.VerifierStep<>("i am a test object hehe"));
    }

    @Test
    void shouldThrowExceptionWhenValidationFails() {
        assertThatThrownBy(() -> Verifier.forObject(4).unless(object -> object == 4).verify()).isInstanceOf(VerifierException.class);
    }

    @Test
    void shouldReturnTestObjectWhenValidationSucceeds() throws VerifierException {
        String obj = Verifier.forObject("hehehe")
                .unless(String::isBlank)
                .unless(String::isEmpty)
                .verify();

        assertThat(obj).isInstanceOf(String.class).isEqualTo("hehehe");
    }

    @Test
    void shouldReturnMaybeWhenValidationSucceeds() {
        Optional<String> obj = Verifier.forObject("hehehe")
                .unless(String::isBlank)
                .unless(String::isEmpty)
                .verifyMaybe();

        assertThat(obj).isNotEmpty().hasValue("hehehe");
    }

    @Test
    void shouldReturnEmptyWhenValidationFails() {
        Optional<Integer> obj = Verifier.forObject(4)
                .unless(object -> object == 4)
                .verifyMaybe();

        assertThat(obj).isEmpty();
    }
}