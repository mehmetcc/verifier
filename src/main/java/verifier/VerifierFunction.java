package verifier;

@FunctionalInterface
public interface VerifierFunction<T> {
    Boolean checkup(T obj);
}
