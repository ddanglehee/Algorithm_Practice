import java.util.*;
import java.io.*;

public class Boj1926 {

    static int n, m;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int max = 0;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                graph[i][j] = input;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    count++;
                    bfs(new int[] {i, j});
                }
            }
        }

        System.out.println(count);
        System.out.println(max);
    }

    static void bfs(int[] start) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[start[0]][start[1]] = true;
        queue.add(start);
        int size = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.removeFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[0] + dr[d];
                int nC = cur[1] + dc[d];

                if (0 <= nR && nR < n && 0 <= nC && nC < m && graph[nR][nC] == 1 && !visited[nR][nC]) {
                    visited[nR][nC] = true;
                    queue.add(new int[] {nR, nC});
                    size++;
                }
            }
        }

        max = Math.max(size, max);
    }
}
