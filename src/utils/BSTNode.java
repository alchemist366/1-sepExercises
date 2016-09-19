package utils;

/**
 * Created by root on 10.09.16.
 */
public class BSTNode<T> {
    public T value;
    public BSTNode<T> right;
    public BSTNode<T> left;

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

//    @Override
//    public boolean equals(Object object) {
//        if (object == null) {
//            return false;
//        } else {
//            BSTNode<T> that = (BSTNode<T>)object;
//
//            String thatString = that.value.toString();
//
//            String thisString = this.value.toString();
//
//            if (thisString.hashCode()  == thatString.hashCode() ) {
//                return true;
//            }
//            return false;
//        }
//    }
}
