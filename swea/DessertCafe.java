import java.util.*;
import java.io.*;

public class DessertCafe {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, answer;
    static int[][] graph = new int[20][20];
    static boolean[][] visited = new boolean[20][20];
    static boolean[] ate = new boolean[101];
    static int[] dCount = new int[4];
    static int[] dr = {-1, 1, 1, -1};
    static int[] dc = {1, 1, -1, -1};
    static int startR, startC;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            init();

            for (int i = 1; i < N - 1; i++) {
                for (int j = 0; j < N - 2; j++) {
                    startR = i; startC = j;
                    dfs(i, j, 0, 0);
                }
            }

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }

    static void dfs(int r, int c, int d, int count) {
        if (d == 3 && r == startR && c == startC) {
            answer = Math.max(answer, count);
            return;
        }

        // 방문 처리
        if (!(r == startR && c == startC)) {
            visited[r][c] = true;
            ate[graph[r][c]] = true;
        }

        // 같은 방향으로
        if (d == 0 || d == 1 || dCount[d] < dCount[d - 2]) {
            int nR = r + dr[d];
            int nC = c + dc[d];
            if (canGo(nR, nC)) {
                dCount[d]++;
                dfs(nR, nC, d, count + 1);
                dCount[d]--;
            }
        }

        // 다른 방향으로
        if (dCount[0] != 0 && d != 3) {
            int nR = r + dr[d + 1];
            int nC = c + dc[d + 1];
            if (canGo(nR, nC)) {
                dCount[d + 1]++;
                dfs(nR, nC, d + 1, count + 1);
                dCount[d + 1]--;
            }
        }

        visited[r][c] = false;
        ate[graph[r][c]] = false;
    }

    static boolean canGo(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N && !visited[r][c] && !ate[graph[r][c]];
    }

    static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int dessert = Integer.parseInt(st.nextToken());
                graph[i][j] = dessert;
                visited[i][j] = false;
            }
        }
        for (int d = 0; d < 4; d++) {
            dCount[d] = 0;
        }
        for (int i = 1; i <= 100; i++) {
            ate[i] = false;
        }
        answer = -1;
    }
}
