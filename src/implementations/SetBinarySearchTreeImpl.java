package implementations;

import interfaces.Set;
import utils.BSTNode;

import java.util.LinkedList;

/**
 * Created by root on 06.09.16.
 */
public class SetBinarySearchTreeImpl<T> implements Set<T> {

    private BSTNode<T> root = new BSTNode<>();

    public boolean add(T element)  {
        boolean added;
        if (element != null) {
            if (root.value == null) {
                root.value = element;
                root.right = new BSTNode<>();
                root.left = new BSTNode<>();
                added = true;
            } else {
                BSTNode<T> node = new BSTNode<>();
                node.value = element;
                added = addNodeToTree(root, node);
            }
        } else {
            return false;
        }
        return added;
    }

    private boolean addNodeToTree(BSTNode<T> rootLocal, BSTNode<T> node) {
        boolean added = true;
        if (rootLocal.value == null) {
            rootLocal.value = node.value;
            rootLocal.right = new BSTNode<>();
            rootLocal.left = new BSTNode<>();
            return true;
        } else if (rootLocal.equals(node)) {
            return false;
        }
        if (rootLocal.hashCode() > node.hashCode()) {
            added = addNodeToTree(rootLocal.left, node);
        }
        if (rootLocal.hashCode() < node.hashCode()) {
            added = addNodeToTree(rootLocal.right, node);
        }
        return added;
    }

    public int find(T element) {
        if (element == null) {
            return -1;
        }
        BSTNode tmp = new BSTNode();
        tmp.value = element;
        int position = findIntoTree(root, tmp);
        return position;
    }

    private int findIntoTree(BSTNode tmpRoot, BSTNode tmp) {
        while (tmpRoot.value != null) {
            if (tmpRoot.value.equals(tmp.value)) {
                return tmpRoot.hashCode();
            } else if (tmp.hashCode() < tmpRoot.hashCode()) {
                tmpRoot = tmpRoot.left;
            } else {
                tmpRoot = tmpRoot.right;
            }
        }

        return -1;
    }

    public boolean delete(T element) {
        if (element == null) {
            return false;
        }
        int position = find(element);
        if (position == -1) {
            return false;
        }
        BSTNode<T> node = new BSTNode<>();
        node.value = element;
        BSTNode rootLocal = root;
        while (position != rootLocal.hashCode()) {
            if (position > element.hashCode()) {
                rootLocal = rootLocal.right;
            } else if (position < node.hashCode()) {
                rootLocal = rootLocal.left;
            }
        }
        deleteNode(rootLocal);
        return true;
    }

    private void deleteNode(BSTNode node) {
        BSTNode copyNode = node;
        if (copyNode.left.value != null) {
            copyNode = copyNode.left;
        }
        while (copyNode.right.value != null) {
            copyNode = copyNode.right;
        }
        node.value = copyNode.value;
        copyNode.value = null;
    }
}
