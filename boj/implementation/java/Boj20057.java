import java.util.*;
import java.io.*;

class Boj20057 {

    private static int answer = 0;
    private static int n;
    private static int[][] graph;
    private static int[] dr = {0, 1, 0, -1};
    private static int[] dc = {-1, 0, 1, 0};
    private static int[][] tr = {
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {-1, -1, 0, 0, 1, 1, 0, 0, 2, 1},
            {1, -1, 1, -1, 1, -1, 2, -2, 0, 0},
            {1, 1, 0, 0, -1, -1, 0, 0, -2, -1}
    };
    private static int[][] tc = {
            {1, 1, 0, 0, -1, -1, 0, 0, -2, -1},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {-1, -1, 0, 0, 1, 1, 0, 0, 2, 1},
            {1, -1, 1, -1, 1, -1, 2, -2, 0, 0}
    };
    private static double[] ratio = {0.01, 0.01, 0.07, 0.07, 0.1, 0.1, 0.02, 0.02, 0.05};

    public static void main(String args[]) throws IOException {
        init();

        int d = 0;
        int c = 1;
        int[] current = {n / 2 + 1, n / 2 + 1};
        while (c <= n) {
            for (int count = 1; count <= 2; count++) {
                for (int i = 1; i <= c; i++) {
                    int[] y = {current[0] + dr[d], current[1] + dc[d]};
                    if (!isInGraph(y[0], y[1])) {
                        System.out.println(answer);
                        return;
                    }

                    tornado(d, y[0], y[1]);
                    current[0] = y[0]; current[1] = y[1];
                }
                d = (d + 1) % 4;
            }
            c++;
        }
    }

    private static void tornado(int d, int r, int c) {
        int remain = graph[r][c];

        for (int i = 0; i <= 9; i++) {
            int newR = r + tr[d][i];
            int newC = c + tc[d][i];
            int sand;
            if (i == 9) {
                sand = remain;
            } else {
                sand = (int) (graph[r][c] * ratio[i]);
            }

            if (!isInGraph(newR, newC)) {
                answer += sand;
            } else {
                graph[newR][newC] += sand;
            }

            remain -= sand;
        }
    }

    private static boolean isInGraph(int r, int c) {
        return 1 <= r && r <= n && 1 <= c && c <= n;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n + 1][n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int sand = Integer.parseInt(st.nextToken());
                graph[i][j] = sand;
            }
        }
    }
}
