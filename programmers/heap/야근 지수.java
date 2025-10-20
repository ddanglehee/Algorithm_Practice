import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            pq.offer(work);
        }

        for (int i = 0; i < n; i++) {
            int a = pq.poll();
            if (a == 0) break;
            a--;
            pq.offer(a);
        }

        while (!pq.isEmpty()) {
            long a = (long) pq.poll();
            answer += (a * a);
        }

        return answer;
    }
}