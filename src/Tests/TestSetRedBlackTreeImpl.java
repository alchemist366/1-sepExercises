package Tests;

import implementations.SetBinarySearchTreeImpl;
import implementations.SetRedBlackTreeImpl;
import org.junit.Test;
import utils.RBNode;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by root on 15.09.16.
 */
public class TestSetRedBlackTreeImpl {

    private int maxBlackLength = 0, minBlackLangth = 2147483647;

    @Test
    public void addIntoRedBlackTree() {
        SetRedBlackTreeImpl<Integer> setRedBlackTree = new SetRedBlackTreeImpl<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(setRedBlackTree.add(i));
        }
        assertFalse(setRedBlackTree.add(null));
        assertFalse(setRedBlackTree.add(456));
    }

    @Test
    public void findIntoRedBlackTree() {
        SetRedBlackTreeImpl<Integer> setRedBlackTree = new SetRedBlackTreeImpl<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(setRedBlackTree.add(i));
        }
        setRedBlackTree.add(1000);
        RBNode<Integer> node = new RBNode<>();
        node.value = 20;
        assertEquals(node.hashCode(), setRedBlackTree.find(20));
    }

    @Test
    public void deleteFromRedBlackTree() {
        SetRedBlackTreeImpl<Integer> setRedBlackTree = new SetRedBlackTreeImpl<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(setRedBlackTree.add(i));
        }
        assertTrue(setRedBlackTree.delete(50));
        assertFalse(setRedBlackTree.delete(50));
        assertFalse(setRedBlackTree.delete(null));
        assertTrue(mainRule(setRedBlackTree.getRoot()));
    }

    private boolean mainRule(RBNode<Integer> root) {
        if (root.red) {
            return false;
        }

        DFS(root, 0);
        return maxBlackLength/minBlackLangth <= 2;
    }

    private int DFS(RBNode<Integer> root, int blackLength) {

        if (!root.red) {
            blackLength ++;
        }

        if (root.value == null) {
            if (maxBlackLength < blackLength) {
                maxBlackLength = blackLength;
            }
            if (minBlackLangth > blackLength) {
                minBlackLangth = blackLength;
            }
            return 0;
        }

        if (root.left != null) {
            DFS(root.left, blackLength);
        }
        if (root.right != null) {
            DFS(root.right, blackLength);
        }
        return blackLength;
    }
}
