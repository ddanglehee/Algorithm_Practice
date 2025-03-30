import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = {0, 0, 0, 0};

        int[] in = new int[1000001];
        int[] out = new int[1000001];
        int vMax = 0;
        ArrayList<Integer>[] map = new ArrayList[1000001];
        for (int i = 1; i <= 1000000; i++) {
            map[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            map[edge[0]].add(edge[1]);
            out[edge[0]]++;
            in[edge[1]]++;
            vMax = Math.max(vMax, Math.max(edge[0], edge[1]));
        }

        for (int i = 1; i <= vMax; i++) {
            if (in[i] == 0 && 2 <= out[i]) {
                answer[0] = i;
            } else if (in[i] >= 2 && out[i] == 2) {
                answer[3]++;
            }
        }

        boolean[] visited = new boolean[1000001];
        for (int start : map[answer[0]]) {
            int cur = start;

            boolean is8 = false;
            while (cur != 0) {
                visited[cur] = true;

                if (map[cur].size() == 0) {
                    cur = 0;
                } else {
                    int nxt = map[cur].get(0);
                    if (in[nxt] >= 2 && out[nxt] == 2) {
                        is8 = true;
                        break;
                    }
                    if (visited[nxt]) break;
                    cur = nxt;
                }
            }

            if (is8) continue;
            if (cur == 0) {
                answer[2]++;
            } else {
                answer[1]++;
            }
        }

        return answer;
    }
}