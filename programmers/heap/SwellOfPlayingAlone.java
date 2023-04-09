import java.util.*;

class Solution {

    public int solution(int[] cards) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        pq.offer(0);
        int tmpBoxCount = 0;
        boolean[] used = new boolean[cards.length];

        for (int i = 0; i < cards.length; i++) {
            if (used[i]) continue;

            int nextBox = i;
            while (!used[nextBox]) {
                used[nextBox] = true;
                nextBox = cards[nextBox] - 1;
                tmpBoxCount++;
            }

            pq.offer(tmpBoxCount);
            tmpBoxCount = 0;
        }

        int answer = pq.poll();
        answer *= pq.poll();

        return answer;
    }
}