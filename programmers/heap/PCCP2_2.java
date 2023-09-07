import java.util.PriorityQueue;

class PCCP2_2 {
    public int solution(int[] ability, int number) {
        int answer = 0;
        PriorityQueue<Integer> abilityPQ = new PriorityQueue<>();
        for (int a: ability) {
            abilityPQ.offer(a);
        }

        for (int edu = 1; edu <= number; edu++) {
            int a = abilityPQ.poll();
            int b = abilityPQ.poll();

            abilityPQ.offer(a + b);
            abilityPQ.offer(a + b);
        }

        while (!abilityPQ.isEmpty()) {
            answer += abilityPQ.poll();
        }

        return answer;
    }
}