package org.fireplume.algorithms.tree;

public class Tree<T extends Comparable> {
    private Node root;

    public static void main(String[] args) {
        Tree<String> tree = new Tree<>();
        tree.add("string2");
        tree.add("string1");
        tree.add("string3");
        tree.add("string5");
        tree.add("string3");
        tree.add("string10");

        System.out.println(tree.toString());
    }

    public Tree() {
    }

    public boolean add(T data) {
        if (root == null) {
            root = new Node<>(data, null);
            return true;
        }
        Node cur = root;
        int compare;
        while (true) {
            compare = cur.data.compareTo(data);
            if (compare > 0) {
                if (cur.right != null) {
                    cur = cur.right;
                } else {
                    cur.right = new Node<>(data, cur);
                    recursiveIncSize(cur.right);
                    return true;
                }
            } else if (compare < 0) {
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    cur.left = new Node<>(data, cur);
                    recursiveIncSize(cur.left);
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return root != null ? root.toString() : null;
    }

    private void recursiveIncSize(final Node leaf) {
        Node node = leaf;
        do {
            node.setSize();
            node = node.getParent();
        } while (node != null);
    }

    private class Node<T extends Comparable> {
        private int size;
        private Node left;
        private Node right;
        private Node parent;
        private T data;

        public Node(T data, Node parent) {
            this.data = data;
            this.parent = parent;
            this.size = 1;
        }

        public T getData() {
            return data;
        }

        public Node getParent() {
            return parent;
        }

        public int getSize() {
            return size;
        }

        public void setSize() {
            if (left != null && right != null)
                size = Math.max(left.getSize(), right.getSize()) + 1;
            else if (left == null && right != null)
                size = right.getSize() + 1;
            else if (right == null && left != null)
                size = left.getSize() + 1;
        }

        @Override
        public String toString() {
            String tabMult = "\t";
            String replace = "\n" + String.format("%0" + size + "d", 0).replace("0", tabMult);
            return "data: " + data + ", size: " + size + ", left: [" + left + "], right: [" + right + "]";
        }
    }
}
