import java.util.Arrays;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] playersCapacity = new int[24]; // i시간대에 감당 가능한 이용자 수
        Arrays.fill(playersCapacity, m - 1);

        for (int t = 0; t < 24; t++) {
            if (players[t] <= playersCapacity[t]) continue;

            int neededServers = (int) Math.ceil((players[t] - playersCapacity[t]) / (double)m);

            for (int i = t; i < t + k; i++) {
                if (24 <= i) break;
                playersCapacity[i] += (m * neededServers);
            }
            answer += neededServers;
        }

        return answer;
    }
}