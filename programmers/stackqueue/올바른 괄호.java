import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.add(c);
            } else {
                if (stack.isEmpty()) {
                    answer = false;
                    break;
                }
                stack.removeLast();
            }
        }
        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }
}