package com.budzko.cookbook.algo.structure.tree;


import edu.princeton.cs.algs4.RedBlackBST;

/**
 * {@link RedBlackBST}
 */
public class LeftLeaningRedBackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = true;

    private Node<K, V> root;

    public static void main(String[] args) {
        LeftLeaningRedBackTree<String, String> tree = new LeftLeaningRedBackTree<>();
        tree.put(null, "1", "v1");
        tree.put(null, "1", "v1");
        System.out.println();
    }

    private Node<K, V> rotateLeft(Node<K, V> parent) {
        Node<K, V> child = parent.right;
        parent.right = child.left;
        child.left = parent;
        child.color = parent.color;
        parent.color = RED;
        return child;
    }

    private Node<K, V> rotateRight(Node<K, V> parent) {
        Node<K, V> child = parent.left;
        parent.left = child.right;
        child.right = parent;
        child.color = parent.color;
        parent.color = RED;
        return child;

    }

    private void flipColors(Node<K, V> parent) {
        parent.color = RED;
        parent.left.color = BLACK;
        parent.right.color = BLACK;
    }

    public void insert(K key, V value) {
        var node = findNode(key);
        put(node, key, value);
    }

    public Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            Node<K, V> result = new Node<>();
            result.color = RED;
            result.key = key;
            result.value = value;
            return result;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else if (cmp == 0) {
            node.value = value;
        }
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }

    private boolean isRed(Node<K, V> node) {
        if (node == null) return false;
        return node.color == RED;
    }

    private Node<K, V> findNode(K key) {
        return null;
    }

    public V find(K key) {
        var node = new Node<>();
        return (V) node.value;
    }
}
