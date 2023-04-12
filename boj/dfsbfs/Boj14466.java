import java.util.*;
import java.io.*;

class Main {

    static int n, k, r;
    static int count = 0;
    static boolean[][] visited;
    static int[][] cow;
    static boolean[][][][] isRoad;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < k; i++) {
            bfs(cow[i][0], cow[i][1]);

            for (int j = i + 1; j < k; j++) {
                if (!visited[cow[j][0]][cow[j][1]]) {
                    count++;
                }
            }
        }

        System.out.print(count);
    }

    public static void bfs(int startR, int startC) {
        initVisited();
        ArrayDeque<int[]> queue = new ArrayDeque<>();

        visited[startR][startC] = true;
        queue.offer(new int[] {startR, startC});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nR = cur[0] + dr[i];
                int nC = cur[1] + dc[i];

                if (0 < nR && nR <= n && 0 < nC && nC <= n && !visited[nR][nC] && !isRoad[cur[0]][cur[1]][nR][nC]) {
                    visited[nR][nC] = true;
                    queue.offer(new int[] {nR, nC});
                }
            }
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        cow = new int[k][2];
        isRoad = new boolean[n+1][n+1][n+1][n+1];
        visited = new boolean[n+1][n+1];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int fR = Integer.parseInt(st.nextToken());
            int fC = Integer.parseInt(st.nextToken());
            int tR = Integer.parseInt(st.nextToken());
            int tC = Integer.parseInt(st.nextToken());

            isRoad[fR][fC][tR][tC] = true;
            isRoad[tR][tC][fR][fC] = true;
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            cow[i] = new int[] {r, c};
        }
    }

    static void initVisited() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                visited[i][j] = false;
            }
        }
    }
}