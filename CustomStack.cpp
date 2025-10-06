#include <iostream>
#include <vector>
using namespace std;

class CustomStack {
private:
    vector<int> stack;
    int maxSize;

public:
    CustomStack(int maxSize) {
        this->maxSize = maxSize;
    }
    
    void push(int x) {
        if (stack.size() < maxSize)
            stack.push_back(x);
    }
    
    int pop() {
        if (stack.empty())
            return -1;
        int topVal = stack.back();
        stack.pop_back();
        return topVal;
    }
    
    void increment(int k, int val) {
        int limit = min(k, (int)stack.size());
        for (int i = 0; i < limit; i++) {
            stack[i] += val;
        }
    }

    // Optional: helper to print stack (for testing)
    void printStack() {
        cout << "[";
        for (size_t i = 0; i < stack.size(); i++) {
            cout << stack[i];
            if (i != stack.size() - 1)
                cout << ", ";
        }
        cout << "]" << endl;
    }
};

// Test main() — you can modify or remove it when testing on LeetCode
int main() {
    CustomStack stk(3);
    stk.push(1);      // [1]
    stk.push(2);      // [1, 2]
    cout << stk.pop() << endl;  // Output: 2 -> [1]
    stk.push(2);      // [1, 2]
    stk.push(3);      // [1, 2, 3]
    stk.push(4);      // ignored, full
    stk.increment(5, 100);  // [101, 102, 103]
    stk.increment(2, 100);  // [201, 202, 103]
    cout << stk.pop() << endl;  // 103
    cout << stk.pop() << endl;  // 202
    cout << stk.pop() << endl;  // 201
    cout << stk.pop() << endl;  // -1
    return 0;
}
