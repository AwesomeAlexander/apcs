package utils;

@FunctionalInterface
public interface CompareTwo<T> {
    public int compare(T a, T b);
}