package Tests;

import implementations.SetArrayImpl;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by root on 07.09.16.
 */
public class TestArrayImpl {

    @Test
    public void addIntoArray() {
        SetArrayImpl<Integer> setArrayImpl = new SetArrayImpl<>();
        for (int i = 0; i < setArrayImpl.getMAX_SIZE(); i++) {

            assertTrue(setArrayImpl.add(i));
        }
        assertFalse(setArrayImpl.add(101));

        setArrayImpl = new SetArrayImpl<>();

        int element = 12;

        assertTrue(setArrayImpl.add(element));
        assertFalse(setArrayImpl.add(element));

        assertTrue(setArrayImpl.add(0));

        assertFalse(setArrayImpl.add(null));
    }

    @Test
    public void deleteFromArray() {
        SetArrayImpl<Integer> setArrayImpl = new SetArrayImpl<>();
        assertFalse(setArrayImpl.delete(0));
        assertFalse(setArrayImpl.delete(null));

        for (int i = 0; i < setArrayImpl.getMAX_SIZE(); i++) {
            assertTrue(setArrayImpl.add(i));
        }
        int element = 3;
        assertTrue(setArrayImpl.delete(element));
        assertFalse(setArrayImpl.delete(element));
    }

    @Test
    public void findIntoArray() {
        SetArrayImpl<Integer> setArrayImpl = new SetArrayImpl<>();
        for (int i = 0; i < setArrayImpl.getMAX_SIZE(); i++) {
            assertTrue(setArrayImpl.add(i));
        }
        assertEquals(1 , setArrayImpl.find(1));
        assertEquals(-1, setArrayImpl.find(100));
        assertEquals(-1, setArrayImpl.find(null));

        setArrayImpl = new SetArrayImpl<>();
        assertEquals(-1, setArrayImpl.find(null));
    }
}
