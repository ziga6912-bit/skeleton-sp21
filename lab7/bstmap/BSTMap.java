package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    private static class BSTNode<K extends Comparable, V> {
        private K key;
        private V value;
        private BSTNode<K, V> left;
        private BSTNode<K, V> right;

        BSTNode(K k, V v) {
            key = k;
            value = v;
            left = null;
            right = null;
        }
    }

    private BSTNode<K, V> root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(root, key);
    }

    private boolean containsKeyHelper(BSTNode<K, V> node, K key) {
        if (node == null) {
            return false;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return containsKeyHelper(node.left, key);
        } else if (cmp > 0) {
            return containsKeyHelper(node.right, key);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(BSTNode<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getHelper(node.left, key);
        } else if (cmp > 0) {
            return getHelper(node.right, key);
        } else {
            return node.value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private BSTNode<K, V> putHelper(BSTNode<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new BSTNode<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putHelper(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putHelper(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    public void printInOrder() {
        printHelper(root);
    }

    private void printHelper(BSTNode<K, V> node) {
        if (node == null) {
            return;
        }
        printHelper(node.left);
        System.out.print(node.key + " ");
        printHelper(node.right);
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Unsupported Operation");
    }
}
