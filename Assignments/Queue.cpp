class MyCircularQueue {
    vector<int> q;
    int front;
    int size;
    int capacity;
public:
    MyCircularQueue(int k) : q(k), front(0), size(0), capacity(k) {}

    bool enQueue(int value) {
        if (size == capacity) return false;
        int idx = (front + size) % capacity;
        q[idx] = value;
        size++;
        return true;
    }

    bool deQueue() {
        if (size == 0) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    int Front() {
        if (size == 0) return -1;
        return q[front];
    }

    int Rear() {
        if (size == 0) return -1;
        int idx = (front + size - 1) % capacity;
        return q[idx];
    }

    bool isEmpty() {
        return size == 0;
    }

    bool isFull() {
        return size == capacity;
    }
};