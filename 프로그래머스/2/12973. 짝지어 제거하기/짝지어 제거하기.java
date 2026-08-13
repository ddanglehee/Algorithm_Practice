import java.util.*;

class Solution
{
    public int solution(String s)
    {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.isEmpty()) {
                stack.offer(s.charAt(i));
            } else {
                if (stack.getLast() == s.charAt(i)) {
                    stack.removeLast();
                } else {
                    stack.offer(s.charAt(i));
                }
            }
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}