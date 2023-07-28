import java.util.*;
import java.io.*;

public class Boj14500 {

    static int n, m;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {-1, 1, 0, 0};
    static int answer = 0;
    static int[][] terrominoR = {
            {0, -1, 0, 0},
            {-1, 0, 1, 0},
            {0, 1, 0, 0},
            {0, 1, -1, 0}
    };
    static int[][] terrominoC = {
            {-1, 0, 1, 0},
            {0, 1, 0, 0},
            {-1, 0, 1, 0},
            {-1, 0, 0, 0}
    };

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(i, j, 1, graph[i][j]);

                for (int k = 0; k < 4; k++) {
                    int sum = 0;
                    for (int l = 0; l < 4; l++) {
                        int nR = i + terrominoR[k][l];
                        int nC = j + terrominoC[k][l];

                        if (isInGraph(nR, nC)) {
                            sum += graph[nR][nC];
                        } else {
                            sum = 0;
                            break;
                        }
                    }
                    answer = Math.max(sum, answer);
                }
            }
        }

        System.out.print(answer);
    }

    private static void dfs(int r, int c, int count, int sum) {
        if (count == 4) {
            answer = Math.max(sum, answer);
            return;
        }

        visited[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (isInGraph(nR, nC) && !visited[nR][nC]) {
                dfs(nR, nC, count + 1, sum + graph[nR][nC]);
            }
        }

        visited[r][c] = false;
    }

    private static boolean isInGraph(int r, int c) {
        return 0 <= r && r < n && 0 <= c && c < m;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
