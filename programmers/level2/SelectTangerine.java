import java.util.*;

class SelectTangerine {

    static private Map<Integer, Integer> tangerineMap = new HashMap<Integer, Integer>();

    public int solution(int k, int[] tangerines) {
        int answer = 0;

        for (int tangerine: tangerines) {
            if (tangerineMap.get(tangerine) == null) {
                tangerineMap.put(tangerine, 1);
            } else {
                tangerineMap.put(tangerine, tangerineMap.get(tangerine) + 1);
            }
        }

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
        for(Map.Entry<Integer, Integer> element: tangerineMap.entrySet()) {
            pq.offer(element.getValue());
        }

        while(!pq.isEmpty()) {
            int count = pq.poll();
            answer++;
            if (count < k) {
                k -= count;
            } else {
                break;
            }
        }

        return answer;
    }
}