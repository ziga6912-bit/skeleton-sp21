package deque;
@SuppressWarnings("unchecked")

public class ArrayDeque<WhirlPool> {

    private int front;
    private int back;
    private int size;
    private int length = 8;
    private WhirlPool[] items;

    public ArrayDeque() {
        items = (WhirlPool[]) new Object[length];
        size = 0;
        front = 4;
        back = 5;
    }

    private void resize() {
        WhirlPool[] newitems = (WhirlPool[]) new Object[2 * length];
        int j = length / 2;
        int start = (front + 1) % length;
        int end = (back - 1 + length) % length;
        if (end < start) {
            end += length;
        }
        for (int i = start; i <= end; i++) {
            newitems[j] = items[i % length];
            j += 1;
        }
        items = newitems;
        front = length / 2 - 1;
        back = j + 1;
        length *= 2;
    }

    public void addFirst(WhirlPool item) {
        size += 1;
        if (size <= length) {
            items[front] = item;;
        } else {
            resize();
            items[front] = item;
        }
        front = (front - 1 + length) % length;
    }

    public void addLast(WhirlPool item) {
        size += 1;
        if (size <= length) {
            items[back] = item;
            resize();
            items[back] = item;
        }
        back = (back + 1) % length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int start = (front + 1) % length;
        int end = (back - 1 + length) % length;
        if (end < start) {
            end += length;
        }
        for (int i = start; i <= end; i++) {
            System.out.print(items[i % length] + " ");
        }
        System.out.println();
    }

    public WhirlPool removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            front = (front + 1) % length;
            return items[front];
        }
    }

    public WhirlPool removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            size -= 1;
            back = (back - 1 + length) % length;
            return items[back];
        }
    }

    public WhirlPool get(int index) {
        return items[((front + 1) % length + index) % length];
    }










































}
