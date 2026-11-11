package deque;

import java.util.Iterator;

public class LinkedListDeque<WhirlPool> implements Iterable<WhirlPool> {

    private static class Node<WhirlPool> {
        public Node<WhirlPool> front;
        public WhirlPool item;
        public Node<WhirlPool> back;

        public Node(Node<WhirlPool> f, WhirlPool w, Node<WhirlPool> b) {
            front = f;
            item = w;
            back = b;
        }
    }

    private int size;
    private Node<WhirlPool> sentinel;

    public LinkedListDeque() {
        sentinel = new Node<WhirlPool>(null, null, null);
        sentinel.front = sentinel;
        sentinel.back = sentinel;
        size = 0;
    }

    public void addFirst(WhirlPool item) {
        Node<WhirlPool> node = new Node<>(null, item, null);
        node.back = sentinel.back;
        sentinel.back.front = node;
        sentinel.back = node;
        node.front = sentinel;
        size += 1;
    }

    public void addLast(WhirlPool item) {
        Node<WhirlPool> node = new Node<>(null, item, null);
        node.front = sentinel.front;
        sentinel.front.back = node;
        sentinel.front = node;
        node.back = sentinel;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node<WhirlPool> node  = sentinel.back;
        while (node != sentinel) {
            System.out.print(node.item + " ");
            node = node.back;
        }
        System.out.println();
    }

    public WhirlPool removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            Node<WhirlPool> node1  = sentinel.back;
            Node<WhirlPool> node2  = sentinel.back.back;
            sentinel.back = node2;
            node2.front = sentinel;
            return node1.item;
        }
    }

    public WhirlPool removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            Node<WhirlPool> node1  = sentinel.front;
            Node<WhirlPool> node2  = sentinel.front.front;
            sentinel.front = node2;
            node2.back = sentinel;
            return node1.item;
        }
    }

    public WhirlPool get(int index) {
        if (isEmpty()) {
            return null;
        } else {
            Node<WhirlPool> node = sentinel;
            for (int i = index; i >=0; i--) {
                node = node.back;
            }
            return node.item;
        }
    }

    public WhirlPool getRecursive(int index) {
        if (isEmpty()) {
            return null;
        } else {
            Node<WhirlPool> node = sentinel.back;
            return getRecursiveHelper(node, index);
            }
    }

    private WhirlPool getRecursiveHelper(Node<WhirlPool> node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.back, index - 1);
    }

    public Iterator<WhirlPool> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<WhirlPool> {
        private Node<WhirlPool> WizPos;

        public LinkedListDequeIterator() {
            WizPos = sentinel.back;
        }

        @Override
        public boolean hasNext() {
            return WizPos.back != sentinel;
        }

        @Override
        public WhirlPool next() {
            WizPos = WizPos.back;
            return WizPos.item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof LinkedListDeque<?> otherLLDeque) {
            if (size != otherLLDeque.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (get(i) != otherLLDeque.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}