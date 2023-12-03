package com.epam.rd.autocode.bstprettyprint;

public class PrintableTreeImpl implements PrintableTree{

    private Node root;
    @Override
    public void add(int i) {
        root = insert(root, i);
    }

    private Node insert(Node root, int value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        return root;
    }
    @Override
    public String prettyPrint() {
        StringBuilder treeSB = new StringBuilder();
        String prefix = root.right == null ? "" : "\n";
        prettyPrintBST(root, new StringBuilder(prefix), treeSB);
        return treeSB.substring(1);
    }

    public void prettyPrintBST(Node root, StringBuilder lineSB, StringBuilder treeSB) {
        if (root == null) return;

        int dataSize = Integer.toString(root.value).length(); // Integer.toString(node.data).length();
        int depth = lineSB.length();
        int i = "\n │".indexOf(lineSB.charAt(depth - 1));
        int j = (root.left != null ? 2 : 0) + (root.right != null ? 1 : 0);

        lineSB.append(" ".repeat(dataSize + 1));
        prettyPrintBST(root.left, lineSB, treeSB);
        lineSB.setLength(depth - 1);

        treeSB.append(lineSB);
        treeSB.append("\n┌└".charAt(i));
        treeSB.append(root.value);
        treeSB.append(" ┐┘┤".charAt(j));

        lineSB.append("\n│ ".charAt(i));
        lineSB.append(" ".repeat(dataSize));
        lineSB.append(" │ │".charAt(j));
        prettyPrintBST(root.right, lineSB, treeSB);
    }
}
