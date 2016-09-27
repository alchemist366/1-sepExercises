package set.utils;

/**
 * Created by root on 14.09.16.
 */
public class RBNode<T> {
    public T value;
    public RBNode<T> right;
    public RBNode<T> left;
    public RBNode<T> parent;
    public boolean red;

    @Override
    public int hashCode() {
        int hash = 0;
        for (int i = 0; i < value.toString().length(); i++) {
            hash += (int) value.toString().charAt(i);
            hash += (hash << 3);
            hash ^= (hash >> 11);
            hash += (hash << 15);
        }
        return hash;
    }
}
