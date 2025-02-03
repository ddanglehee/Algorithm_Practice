import java.io.*;
import java.util.*;

public class Boj1976 {

    static int N, M;
    static int[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] mArray = new int[M];
        for (int i = 0; i < M; i++) {
            mArray[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            int from = mArray[i], to = mArray[i + 1];
            if (map[from][to] == 1) continue;

            initVisited();
            if (!dfs(from, from, to)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void initVisited() {
        for (int i = 1; i <= N; i++) {
            visited[i] = false;
        }
    }

    private static boolean dfs(int from, int cur, int to) {
        visited[cur] = true;
        if (cur == to) return true;

        for (int i = 1; i <= N; i++) {
            if (map[cur][i] == 1 && !visited[i]) {
                map[from][i] = 1;
                map[i][from] = 1;
                if (dfs(from, i, to)) return true;
            }
        }

        return false;
    }
}
