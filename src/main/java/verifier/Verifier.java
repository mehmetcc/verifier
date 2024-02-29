package verifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Verifier {
    public static <T> VerifierStep<T> forObject(T obj) {
        return new VerifierStep<>(obj);
    }

    public static class VerifierStep<T> {
        private final List<VerifierFunction<T>> applications;

        private final T obj;

        protected VerifierStep(final T obj) {
            this.applications = new ArrayList<>();
            this.obj = obj;
        }

        private VerifierStep(final List<VerifierFunction<T>> pastApplications, final T obj) {
            this.applications = pastApplications;
            this.obj = obj;
        }

        public VerifierStep<T> unless(final VerifierFunction<T> application) {
            applications.add(application);
            return new VerifierStep<T>(applications, obj);
        }

        public T verify() throws VerifierException {
            var set = applications.stream()
                    .map(current -> current.checkup(obj))
                    .collect(Collectors.<Boolean>toSet());

            if (set.contains(true))
                throw new VerifierException("Verification failed for object: %s".formatted(obj.toString()));
            else
                return obj;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            VerifierStep<?> that = (VerifierStep<?>) o;
            return Objects.equals(applications, that.applications) && Objects.equals(obj, that.obj);
        }
    }
}
