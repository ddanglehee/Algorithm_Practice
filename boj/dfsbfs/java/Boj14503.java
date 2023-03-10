import java.util.*;
import java.io.*;

public class Boj14503 {

    static int n, m;
    static int r, c, d;
    static int[][] graph;
    static boolean[][] isCleared;
    static int answer = 0;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String args[]) throws IOException {
        init();
        dfs(r, c, d);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isCleared[i][j]) answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean dfs(int currentR, int currentC, int currentD) {
        boolean hasEmptyRoom = false;
        isCleared[currentR][currentC] = true;

        // 4가지 방향 중 다음칸에 청소되지 않은 빈 칸이 있으면
        for (int i = 1; i <= 4; i++) {
            int nextD = currentD - i;
            if (nextD < 0) nextD += 4;
            int nextR = currentR + dr[nextD];
            int nextC = currentC + dc[nextD];
            if (isEmptyRoom(nextR, nextC) && !isCleared[nextR][nextC]) {
                hasEmptyRoom = true;
                if (dfs(nextR, nextC, nextD)) return true;
            }
        }

        // 4가지 방향 중 다음 칸에 청소되지 않은 빈 칸이 없으면 후진
        if (!hasEmptyRoom) {
            int nextD = (currentD + 2) % 4;
            int nextR = currentR + dr[nextD];
            int nextC = currentC + dc[nextD];
            if (isEmptyRoom(nextR, nextC)) {
                dfs(nextR, nextC, currentD);
            }
        }
        return true;
    }

    private static boolean isEmptyRoom(int nextR, int nextC) {
        if (0 <= nextR && nextR < n && 0 <= nextC && nextC < m && graph[nextR][nextC] == 0) {
            return true;
        }
        return false;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        isCleared = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int status = Integer.parseInt(st.nextToken());
                graph[i][j] = status;
            }
        }
    }
}
