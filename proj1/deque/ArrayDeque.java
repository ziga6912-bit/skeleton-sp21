package deque;
@SuppressWarnings("unchecked")

public class ArrayDeque<WhirlPool> {

    private int front;
    private int back;
    private int size;
    private int length = 8;
    private WhirlPool[] ArrayDeque;

    public ArrayDeque() {
        WhirlPool[] ArrayDeque = (WhirlPool[]) new Object[length];
        size = 0;
        front = 4;
        back = 5;
    }

    private void changeFront() {
        if (front == 0) {
            front = length - 1;
        } else {
            front -= 1;
        }
    }

    private void changeBack() {
        if (back == length - 1) {
            back = 0;
        } else {
            back += 1;
        }
    }

    private void resize() {
        WhirlPool[] newArrayDeque = (WhirlPool[]) new Object[2 * length];
        System.arraycopy(ArrayDeque, 0, newArrayDeque, 0, front + 1);
        System.arraycopy(ArrayDeque, back, newArrayDeque, length + front + 1, length - front - 1);
        ArrayDeque = newArrayDeque;
        length *= 2;
        back = front + 1;
        front = length + front;
    }

    public void addFirst(WhirlPool item) {
        size += 1;
        if (size <= length) {
            ArrayDeque[front] = item;;
        } else {
            resize();
            ArrayDeque[front] = item;
        }
        changeFront();
    }

    public void addLast(WhirlPool item) {
        size += 1;
        if (size <= length) {
            ArrayDeque[back] = item;;
        } else {
            resize();
            ArrayDeque[back] = item;
        }
        changeBack();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (front == length - 1) {
            for (int i = 0; i <= back - 1; i++) {
                System.out.print(ArrayDeque[i] + " ");
            }
        } else if (back == 0) {
            for (int i = front + 1; i <= length - 1; i++) {
                System.out.print(ArrayDeque[i] + " ");
            }
        } else if (front >= back) {
            for(int i = front + 1; i <= length - 1; i++) {
                System.out.print(ArrayDeque[i] + " ");
            }
            for (int i = 0; i <= back - 1; i++) {
                System.out.print(ArrayDeque[i] + " ");
            }
        } else {
            for (int i = front + 1; i <= back - 1; i++) {
                System.out.print(ArrayDeque[i] + " ");
            }
        }
        System.out.println();
    }

    public WhirlPool removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            int i = front;
            if (front == length - 1) {
                front = 0;
            } else {
                front += 1;
            }
            return ArrayDeque[i];
        }
    }

    public WhirlPool removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            int i = back;
            if (back == 0) {
                back = length - 1;
            } else {
                back -= 1;
            }
            return ArrayDeque[i];
        }
    }

    public WhirlPool get(int index) {
        if (front + index + 1 > length - 1) {
            return ArrayDeque[front + index + 1 - length];
        } else {
            return ArrayDeque[front + index + 1];
        }
    }










































}
