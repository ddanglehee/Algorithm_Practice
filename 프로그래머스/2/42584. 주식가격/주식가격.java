import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int i = 0; i < prices.length; i++) {
            if (!stack.isEmpty() && stack.peekLast()[1] > prices[i]) {
                while(!stack.isEmpty() && prices[i] < stack.peekLast()[1]) {
                    int[] cur = stack.pollLast();
                    answer[cur[0]] = i - cur[0];
                }
            }
            stack.add(new int[] {i, prices[i]});
        }
        
        while(!stack.isEmpty()) {
            int[] cur = stack.pollLast();
            answer[cur[0]] = prices.length - 1 - cur[0];
        }
        
        return answer;
    }
}