// File: CircularQueueDemo.java

class MyCircularQueue {
    private int[] data;
    private int head, tail, size, capacity;

    public MyCircularQueue(int k) {
        capacity = k;
        data = new int[k];
        head = 0;
        tail = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) return false;
        tail = (tail + 1) % capacity;
        data[tail] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) return -1;
        return data[head];
    }

    public int Rear() {
        if (isEmpty()) return -1;
        return data[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

public class CircularQueueDemo {
    public static void main(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(3);
        System.out.println(myCircularQueue.enQueue(1)); // true
        System.out.println(myCircularQueue.enQueue(2)); // true
        System.out.println(myCircularQueue.enQueue(3)); // true
        System.out.println(myCircularQueue.enQueue(4)); // false
        System.out.println(myCircularQueue.Rear());     // 3
        System.out.println(myCircularQueue.isFull());   // true
        System.out.println(myCircularQueue.deQueue());  // true
        System.out.println(myCircularQueue.enQueue(4)); // true
        System.out.println(myCircularQueue.Rear());     // 4
    }
}
