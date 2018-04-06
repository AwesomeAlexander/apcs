package utils;

/**
 * Never mind, {@code java.util.function.Function} Already exists.
 */
@Deprecated
@FunctionalInterface
public interface Function<T, R> {
    // R is return, T is type of input
    public R run(T t);
}