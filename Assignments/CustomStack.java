public class CustomStack {
    
}
class Stack {
    private int maxSize;
    private int[] stack;
    private int[] inc;
    private int top;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        inc = new int[maxSize];
        top = 0;
    }

    public void push(int x) {
        if (top < maxSize) {
            stack[top] = x;
            inc[top] = 0;
            top++;
        }
    }

    public int pop() {
        if (top == 0) {
            return -1;
        }
        top--;
        int res = stack[top] + inc[top];
        if (top > 0) {
            inc[top - 1] += inc[top];
        }
        inc[top] = 0;
        return res;
    }

    public void increment(int k, int val) {
        int idx = Math.min(top, k) - 1;
        if (idx >= 0) {
            inc[idx] += val;
        }
    }
}