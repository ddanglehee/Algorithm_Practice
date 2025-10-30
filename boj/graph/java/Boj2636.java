import java.util.*;
import java.io.*;

public class Boj2636 {

    static int n,m;
    static int[][] map;
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static boolean[][] visited;
    static int meltCheeseCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a;
            }
        }

        int t = 1;

        while (true) {
            meltCheeseCount = 0;
            initVisited();
            dfs(0, 0);
            if (!cheeseExists()) {
                break;
            }
            t++;
        }

        System.out.println(t);
        System.out.println(meltCheeseCount);
    }

    private static boolean cheeseExists() {
        boolean result = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    map[i][j] = 0;
                    meltCheeseCount++;
                } else if (map[i][j] == 1) {
                    result = true;
                }
            }
        }
        return result;
    }

    private static void dfs(int a, int b) {
        visited[a][b] = true;

        for (int d = 0; d < 4; d++) {
            int nR = a + dr[d];
            int nC = b + dc[d];

            if (0 <= nR && nR < n && 0 <= nC && nC < m && !visited[nR][nC] && map[nR][nC] != 2) {
                if (map[nR][nC] == 1) { // 공기에 인접해있는 치즈인 경우
                    map[nR][nC] = 2; // 녹을 치즈임을 표시
                    continue;
                }
                dfs(nR, nC);
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void initVisited() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = false;
            }
        }
    }
}