import java.util.*;
import java.io.*;

public class Boj2665 {

    static int n;
    static char[][] graph;
    static int[][] count;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        System.out.print(count[n-1][n-1]);
    }

    private static void dijkstra() {
        count[0][0] = 0;
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparing(Info::getCount));
        pq.add(new Info(0, 0, 0));

        while (!pq.isEmpty()) {
            Info curInfo = pq.poll();

            if (curInfo.r == n - 1 && curInfo.c == n - 1) return;
            if (count[curInfo.r][curInfo.c] < curInfo.count) continue;

            for (int d = 0; d < 4; d++) {
                int nR = curInfo.r + dr[d];
                int nC = curInfo.c + dc[d];

                if (nR < 0 || n <= nR || nC < 0 || n <= nC) continue;
                if (graph[nR][nC] == '1' && curInfo.count < count[nR][nC]) {
                    count[nR][nC] = curInfo.count;
                    pq.add(new Info(nR, nC, count[nR][nC]));
                } else if (curInfo.count + 1 < count[nR][nC]) {
                    count[nR][nC] = curInfo.count + 1;
                    pq.add(new Info(nR, nC, count[nR][nC]));
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new char[n][n];
        count = new int[n][n];
        for (int i = 0; i < n; i++) {
            graph[i] = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                count[i][j] = 2500;
            }
        }
    }

    static class Info {
        int r;
        int c;
        int count;

        public Info(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }
}
