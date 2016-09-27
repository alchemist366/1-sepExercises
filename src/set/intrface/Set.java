package set.intrface;

/**
 * Created by root on 06.09.16.
 */
public interface Set<T> {
    boolean add(T element);
    int find(T element);
    boolean delete(T element);
}
