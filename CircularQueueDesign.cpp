#include <iostream>
#include <vector>
using namespace std;

class MyCircularQueue {
private:
    vector<int> q;
    int front, rear, size, capacity;

public:
    MyCircularQueue(int k) {
        capacity = k;
        q.resize(k);
        front = 0;
        rear = -1;
        size = 0;
    }

    bool enQueue(int value) {
        if (isFull()) return false;
        rear = (rear + 1) % capacity;
        q[rear] = value;
        size++;
        return true;
    }

    bool deQueue() {
        if (isEmpty()) return false;
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    int Front() {
        if (isEmpty()) return -1;
        return q[front];
    }

    int Rear() {
        if (isEmpty()) return -1;
        return q[rear];
    }

    bool isEmpty() {
        return size == 0;
    }

    bool isFull() {
        return size == capacity;
    }
};

// Example usage
int main() {
    MyCircularQueue myCircularQueue(3);
    cout << boolalpha;
    cout << myCircularQueue.enQueue(1) << endl; // true
    cout << myCircularQueue.enQueue(2) << endl; // true
    cout << myCircularQueue.enQueue(3) << endl; // true
    cout << myCircularQueue.enQueue(4) << endl; // false
    cout << myCircularQueue.Rear() << endl;     // 3
    cout << myCircularQueue.isFull() << endl;   // true
    cout << myCircularQueue.deQueue() << endl;  // true
    cout << myCircularQueue.enQueue(4) << endl; // true
    cout << myCircularQueue.Rear() << endl;     // 4
    return 0;
}
