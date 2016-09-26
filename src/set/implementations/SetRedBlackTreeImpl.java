package set.implementations;

import set.intrface.Set;
import set.utils.RBNode;

/**
 * Created by root on 06.09.16.
 */
public class SetRedBlackTreeImpl<T> implements Set<T> {

    private RBNode<T> root = new RBNode<>();

    public RBNode<T> getRoot() {
        return root;
    }

    private void fillLeaf(RBNode<T> leaf, RBNode<T> parent) {
        leaf.red = true;
        if (parent.hashCode() > leaf.hashCode()) {
            parent.left = leaf;
        } else {
            parent.right = leaf;
        }
        leaf.parent = parent;
        leaf.right = new RBNode<>();
        leaf.right.red = false;
        leaf.left = new RBNode<>();
        leaf.left.red = false;
    }

    public boolean add(T element) {
        boolean added;
        if (element != null) {
            if (root.value == null) {
                root.value = element;
                root.parent = null;
                root.red = false;
                root.right = new RBNode<>();
                root.right.red = false;
                root.left = new RBNode<>();
                root.left.red = false;
                added = true;
            } else {
                RBNode<T> node = new RBNode<>();
                node.value = element;
                added = addNodeToTree(root, node, root);
            }
        } else {
            return false;
        }
        return added;
    }

    private boolean addNodeToTree(RBNode<T> rootLocal, RBNode<T> node, RBNode<T> parent) {
        boolean added = true;
        if (rootLocal.value == null) {
            fillLeaf(node, parent);
            insertFixup(node);
            return true;
        }
        if (rootLocal.value.equals(node.value)) {
            return false;
        }
        if (rootLocal.hashCode() > node.hashCode()) {
            added = addNodeToTree(rootLocal.left, node, rootLocal);
        }
        if (rootLocal.hashCode() < node.hashCode()) {
            added = addNodeToTree(rootLocal.right, node, rootLocal);
        }
        return added;
    }

    private void rotateLeft(RBNode<T> x) {

        /**************************
         *  rotate node x to left *
         **************************/

        RBNode<T> y = x.right;

    /* establish x->right link */
        x.right = y.left;


        if (y.left != null) {
            y.left.parent = x;
        }

    /* establish y->parent link */
        if (y.value != null) {
            y.parent = x.parent;
        }

    /* establish y->parent link */
        y.parent = x.parent;
        if (x.parent != null) {
            if (x.parent.left.equals(x))
                x.parent.left = y;
            else
                x.parent.right = y;
        } else {
            root = y;
        }

    /* link x and y */
        y.left = x;
        x.parent = y;
    }

    private void rotateRight(RBNode<T> x) {
        RBNode<T> y = x.left;

        x.left = y.right;

        if (y.right != null) {
            y.right.parent = x;
        }

    /* establish y->parent link */
        if (y.value != null) {
            y.parent = x.parent;
        }
        if (x.parent != null) {
            if (x.parent.left.equals(x)) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        } else {
            root = y;
        }

        y.right = x;
        x.parent = y;
    }

    void insertFixup(RBNode<T> x) {

        /*************************************
         *  maintain Red-Black tree balance  *
         *  after inserting node x           *
         *************************************/

    /* check Red-Black properties */
        while (x != root && x.parent.red) {
        /* we have a violation */
            if (x.parent.parent.left.equals(x.parent)) {
                RBNode<T> y = x.parent.parent.right;
                if (y.red) {

                /* uncle is RED */
                    x.parent.red = false;
                    y.red = false;
                    x.parent.parent.red = true;
                    x = x.parent.parent;
                } else {

                /* uncle is BLACK */
                    if (x.parent.right.equals(x)) {
                    /* make x a left child */
                        x = x.parent;
                        rotateLeft(x);
                    }

                /* recolor and rotate */
                    x.parent.red = false;
                    x.parent.parent.red = true;
                    rotateRight(x.parent.parent);
                }
            } else {

            /* mirror image of above code */
                RBNode<T> y = x.parent.parent.left;
                if (y.red) {

                /* uncle is RED */
                    x.parent.red = false;
                    y.red = false;
                    x.parent.parent.red = true;
                    x = x.parent.parent;
                } else {

                /* uncle is BLACK */
                    if (x.parent.left.equals(x)) {
                        x = x.parent;
                        rotateRight(x);
                    }
                    x.parent.red = false;
                    x.parent.parent.red = true;
                    rotateLeft(x.parent.parent);
                }
            }
        }
        root.red = false;
    }
    public int find(T element) {
        if (element == null) {
            return -1;
        }
        RBNode<T> tmp = new RBNode();
        tmp.value = element;
        int position = findIntoTree(root, tmp);
        return position;
    }

    private int findIntoTree(RBNode tmpRoot, RBNode tmp) {
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
        RBNode<T> node = new RBNode<>();
        node.value = element;
        RBNode rootLocal = root;
        while (position != rootLocal.hashCode()) {
            if (position > rootLocal.hashCode()) {
                rootLocal = rootLocal.right;
            } else if (position < rootLocal.hashCode()) {
                rootLocal = rootLocal.left;
            }
        }
        deleteNode(rootLocal);
        return true;
    }

    void deleteFixup(RBNode<T> x) {

        /*************************************
         *  maintain Red-Black tree balance  *
         *  after deleting node x            *
         *************************************/

        while (x != root && !x.red) {
            if (x.parent.left.equals(x)) {
                RBNode<T> w = x.parent.right;
                if (!w.red) {
                    w.red = false;
                    x.parent.red = true;
                    rotateLeft (x.parent);
                    w = x.parent.right;
                }
                if (!w.left.red && !w.right.red) {
                    w.red = true;
                    x = x.parent;
                } else {
                    if (!w.right.red) {
                        w.left.red = false;
                        w.red = true;
                        rotateRight (w);
                        w = x.parent.right;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.right.red = false;
                    rotateLeft (x.parent);
                    x = root;
                }
            } else {
                RBNode<T> w = x.parent.left;
                if (w.red) {
                    w.red = false;
                    x.parent.red = true;
                    rotateRight (x.parent);
                    w = x.parent.left;
                }
                if (!w.right.red && !w.left.red) {
                    w.red = true;
                    x = x.parent;
                } else {
                    if (!w.left.red) {
                        w.right.red = false;
                        w.red = true;
                        rotateLeft (w);
                        w = x.parent.left;
                    }
                    w.red = x.parent.red;
                    x.parent.red = false;
                    w.left.red = false;
                    rotateRight (x.parent);
                    x = root;
                }
            }
        }
        x.red = false;
    }

    private void deleteNode(RBNode<T> z) {
        RBNode<T> x, y;
        /*****************************
         *  delete node z from tree  *
         *****************************/

        if (z.left.value == null || z.right.value == null) {
        /* y has a NIL node as a child */
            y = z;
        } else {
        /* find tree successor with a NIL node as a child */
            y = z.right;
            while (y.left.value != null) { y = y.left;}
        }

    /* x is y's only child */
        if (y.left.value != null) {
            x = y.left;
        }
        else {
            x = y.right;
        }

    /* remove y from the parent chain */
        x.parent = y.parent;
        if (y.parent != null)
            if (y.parent.left.equals(y)) {
                y.parent.left = x;
            }
            else {
                y.parent.right = x;
            }
        else {
            root = x;
        }

        if (y != z) { z.value = y.value; }


        if (!y.red) {
            deleteFixup(x);
        }

        RBNode<T> node = new RBNode<>();
        if (y.parent.left.equals(y)) {
            y.parent.left = node;
        } else {
            y.parent.right = node;
        }
        node.red = false;
        node.right = null;
        node.left = null;
        node.value = null;
    }
}
