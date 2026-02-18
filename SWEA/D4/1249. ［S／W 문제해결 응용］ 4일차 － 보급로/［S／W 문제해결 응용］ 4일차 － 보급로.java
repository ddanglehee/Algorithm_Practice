import java.util.*;
import java.io.*;

class Solution {

    static int N;
    static int[][] map = new int[100][100];
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] dist = new int[100][100];
    static final int INF = 1_000_000_000;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        return Integer.compare(o1[2], o2[2]);
    });

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            init();
            N = Integer.parseInt(br.readLine());

            for (int r = 0; r < N; r++) {
                String input = br.readLine();
                for (int c = 0; c < N; c++) {
                    map[r][c] = input.charAt(c) - '0';
                }
            }

            dijkstra();

            sb.append("#").append(t).append(" ").append(dist[N-1][N-1]).append("\n");
        }

        System.out.print(sb);
    }

    static void dijkstra() {
        dist[0][0] = 0;
        pq.offer(new int[] {0, 0, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int r = cur[0], c = cur[1], t = cur[2];

            if (dist[r][c] < t) continue;

            for (int d = 0; d < 4; d++) {
                int nR = r + dr[d];
                int nC = c + dc[d];

                if (nR < 0 || nR >= N || nC < 0 || nC >= N) continue;
                if (t + map[nR][nC] < dist[nR][nC]) {
                    dist[nR][nC] = t + map[nR][nC];
                    pq.offer(new int[] {nR, nC, dist[nR][nC]});
                }
            }
        }
    }

    static void init() {
        for (int i = 0; i < 100; i++) {
            Arrays.fill(dist[i], INF);
        }
        pq.clear();
    }
}