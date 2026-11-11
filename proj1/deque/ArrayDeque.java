package deque;

import java.util.Iterator;

@SuppressWarnings("unchecked")

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private int front;
    private int back;
    private int size;
    private int length = 8;
    private T[] items;

    public ArrayDeque() {
        items = (T[]) new Object[length];
        size = 0;
        front = 4;
        back = 5;
    }

    private void sizeup() {
        T[] otheritems = (T[]) new Object[2 * length];
        for (int i = 0; i < size; i++) {
            otheritems[i] = get(i);
        }
        items = otheritems;
        front = length / 2 - 1;
        back = length / 2 + size;
        length *= 2;
    }

    private void sizedown() {
        T[] otheritems = (T[]) new Object[length / 4];
        for (int i = 0; i < size; i++) {
            otheritems[i] = get(i);
        }
        items = otheritems;
        length /= 2;
        front = length - 1;
        back = 0;
    }

    @Override
    public void addFirst(T item) {
        size += 1;
        if (size <= length) {
            items[front] = item;
        } else {
            sizeup();
            items[front] = item;
        }
        front = (front - 1 + length) % length;
    }

    @Override
    public void addLast(T item) {
        size += 1;
        if (size <= length) {
            items[back] = item;
        } else {
            sizeup();
            items[back] = item;
        }
        back = (back + 1) % length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            if (length >= 16 && size / (double) length < 0.25) {
                sizedown();
            }
            front = (front + 1) % length;
            return items[front];
        }
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            if (length >= 16 && size / (double) length < 0.25) {
                sizedown();
            }
            back = (back - 1 + length) % length;
            return items[back];
        }
    }

    @Override
    public T get(int index) {
        return items[((front + 1) % length + index) % length];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int wizPos;

        public ArrayDequeIterator() {
            wizPos = (front + 1) % length;
        }

        @Override
        public boolean hasNext() {
            return wizPos != (back - 1 + length) % length;
        }

        @Override
        public T next() {
            wizPos = (wizPos + 1) % length;
            return items[(wizPos - 1 + length) % length];
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deque<?>) {
            ArrayDeque<?> otherArrayDeque = (ArrayDeque<?>) o;
            if (size != otherArrayDeque.size()) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!get(i).equals(otherArrayDeque.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
