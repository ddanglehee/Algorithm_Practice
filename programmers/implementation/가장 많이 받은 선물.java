import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        HashMap<String, Integer> friendMap = new HashMap();
        for (int i = 0; i < n; i++) {
            friendMap.put(friends[i], i);
        }
        int[][] p = new int[n][n];
        StringTokenizer st;
        for(int i = 0; i < gifts.length; i++) {
            st = new StringTokenizer(gifts[i]);
            int from = friendMap.get(st.nextToken());
            int to = friendMap.get(st.nextToken());
            p[from][to]++;
        }

        int[] tp = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tp[i] += p[i][j];
                tp[j] -= p[i][j];
            }
        }

        int[] receiveCount = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int ij = p[i][j];
                int ji = p[j][i];

                if (ij < ji) {
                    receiveCount[j]++;
                } else if (ij > ji) {
                    receiveCount[i]++;
                } else {
                    if (tp[i] > tp[j]) {
                        receiveCount[i]++;
                    } else if (tp[i] < tp[j]) {
                        receiveCount[j]++;
                    }
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, receiveCount[i]);
        }

        return answer / 2;
    }
}