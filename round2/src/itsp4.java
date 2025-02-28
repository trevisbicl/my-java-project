import java.util.Stack;

class itsp4 {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
//    public static void main(String[] args) {
//        itsp4 solution = new itsp4();
//
//        System.out.println(solution.isValid("()")); // true
//        System.out.println(solution.isValid("()[]{}")); // true
//        System.out.println(solution.isValid("(]")); // false
//        System.out.println(solution.isValid("([])")); // true
//    }
}
