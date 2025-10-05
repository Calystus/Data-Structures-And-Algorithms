public class PalindromeNumber {
    public static boolean isPalindrome(int x) {
        // Negative numbers are not palindrome
        if (x < 0) return false;

        // Convert number to string
        String numStr = Integer.toString(x);

        // Check palindrome by comparing characters
        int left = 0;
        int right = numStr.length() - 1;

        while (left < right) {
            if (numStr.charAt(left) != numStr.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    // Main method to test
    public static void main(String[] args) {
        int x1 = 121;
        int x2 = -121;
        int x3 = 10;

        System.out.println(x1 + " -> " + isPalindrome(x1)); // true
        System.out.println(x2 + " -> " + isPalindrome(x2)); // false
        System.out.println(x3 + " -> " + isPalindrome(x3)); // false
    }
}

