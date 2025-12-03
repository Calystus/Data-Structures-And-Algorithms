public class CircularQueue {
    
}

class MyCircularQueue {
    private int[] q;
    private int front;
    private int size;
    private int capacity;

    public MyCircularQueue(int k) {
        capacity = k;
        q = new int[k];
        front = 0;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (size == capacity) return false;
        int idx = (front + size) % capacity;
        q[idx] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (size == 0) return -1;
        return q[front];
    }

    public int Rear() {
        if (size == 0) return -1;
        int idx = (front + size - 1) % capacity;
        return q[idx];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}