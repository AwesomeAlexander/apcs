package utils;

@FunctionalInterface
public interface Function<T, R> {
    // R is return, T is type of input
    public R run(T t);
}