package set.implementations;

import set.intrface.Set;

/**
 * Created by root on 06.09.16.
 */
public class SetArrayImpl<T> implements Set<T> {

    private final int MAX_SIZE = 100;
    private Object[] array = new Object[MAX_SIZE];


    public boolean add(T element)  {
        boolean added = false, repeated = false;
        if (element != null) {
            for (int i = 0; i < MAX_SIZE && !repeated && !added; i++) {
                if (array[i] == null) {
                    array[i] = element;
                    added = true;
                }
                if (array[i].equals(element)) {
                    repeated = true;
                }
            }
        } else {
            return false;
        }
        return added;
    }

    public int find(T element) {
        int position = -1;
        for (int i = 0; i < MAX_SIZE && array[i] != null; i++) {
            if (array[i].equals(element)) {
                position = i;
            }
        }
        return position;
    }

    public boolean delete(T element) {
        int position = find(element);
        if (position != -1) {
            for (int i = position; i < (MAX_SIZE - 1) && array[i] != null; i++) {
                array[i] = array[i + 1];
            }
        } else {
            return false;
        }
        return true;
    }

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }
}
