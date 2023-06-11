package com.budzko.cookbook.algo.structure.tree;

public class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;
    boolean color;
}
