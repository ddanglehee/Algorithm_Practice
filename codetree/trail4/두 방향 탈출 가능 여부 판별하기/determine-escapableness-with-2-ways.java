import java.util.*;
import java.io.*;

public class Main {

    static int[] dr = {1, 0};
    static int[] dc = {0, 1};
    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int r, int c) {
        if (r == N - 1 && c == M - 1) return 1;
        
        visited[r][c] = true;

        for (int d = 0; d < 2; d++) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (0 <= nR && nR < N && 0 <= nC && nC < M && map[nR][nC] == 1 && !visited[nR][nC]) {
                if (dfs(nR, nC) == 1) return 1;
            }
        }

        return 0;
    }
}