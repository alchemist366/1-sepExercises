package Tests;

import implementations.SetBinarySearchTreeImpl;
import org.junit.Test;
import utils.BSTNode;

import javax.xml.soap.Node;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by root on 10.09.16.
 */
public class TestSetBinarySearchTreeImpl {

    @Test
    public void addIntoBinarySearchTree() {
        SetBinarySearchTreeImpl<Integer> setBinarySearchTree = new SetBinarySearchTreeImpl<>();
        for (int i = 0; i < 100; i++) {
            assertTrue(setBinarySearchTree.add(i));
        }
        int a = 0;
    }

    @Test
    public void findIntoTree() {
        SetBinarySearchTreeImpl<Integer> setBinarySearchTree = new SetBinarySearchTreeImpl<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(setBinarySearchTree.add(i));
        }
        setBinarySearchTree.add(1000);
        BSTNode<Integer> node = new BSTNode<>();
        node.value = 20;
        assertEquals(node.hashCode(), setBinarySearchTree.find(20));
    }

    @Test
    public void deleteFromTree() {
        SetBinarySearchTreeImpl<Integer> setBinarySearchTree = new SetBinarySearchTreeImpl<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(setBinarySearchTree.add(i));
        }
        assertTrue(setBinarySearchTree.delete(6));
        assertFalse(setBinarySearchTree.delete(6));
        assertFalse(setBinarySearchTree.delete(null));
    }
}