package com.laravelshao.learning.structures.tree.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树遍历(前序、中序、后序)
 *
 * @author shaoqinghua
 * @date 2019/5/18
 * @description
 */
public class BinaryTreeTest {

    private static Node root;

    private static List<Node> list = new ArrayList<>();

    public static void main(String[] args) {
        init();
        //preOrder(root); // 前序
        //inOrder(root); // 中序
        postOrder(root); // 后序
        list.forEach(node -> System.out.println(node.data));
    }

    /**
     * 初始化二叉树，先从叶节点开始，由叶到根
     */
    public static void init() {
        Node b = new Node("b", null, null);
        Node a = new Node("a", null, b);
        Node c = new Node("c", a, null);

        Node e = new Node("e", null, null);
        Node g = new Node("g", null, null);
        Node f = new Node("f", e, g);
        Node h = new Node("h", f, null);

        Node d = new Node("d", c, h);

        Node j = new Node("j", null, null);
        Node k = new Node("k", j, null);
        Node m = new Node("m", null, null);
        Node o = new Node("o", null, null);
        Node p = new Node("p", o, null);
        Node n = new Node("n", m, p);
        Node l = new Node("l", k, n);

        root = new Node("i", d, l);
    }

    /**
     * 二叉树前序遍历(根结点->左子树->右子树)
     *
     * @param node
     */
    public static void preOrder(Node node) {
        list.add(node); // 先将根节点存入list
        // 如果左子树不为空继续往左找，在递归调用方法的时候一直会将子树的根存入list，这就做到了先遍历根节点
        if (node.left != null) {
            preOrder(node.left);
        }
        // 无论走到哪一层，只要当前节点左子树为空，那么就可以在右子树上遍历，保证了根左右的遍历顺序
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * 二叉树中序遍历(左子树->根结点->右子树)
     */
    public static void inOrder(Node node) {
        if (node.left != null) {
            inOrder(node.left);
        }
        list.add(node);
        if (node.right != null) {
            inOrder(node.right);
        }
    }

    /**
     * 二叉树后序遍历(左子树->右子树->根结点)
     *
     * @param node
     */
    public static void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        list.add(node);
    }

    /**
     * 节点
     */
    static class Node {

        private Object data;
        private Node left; // 左子树
        private Node right; // 右子树

        public Node() {
        }

        public Node(Object data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

}
