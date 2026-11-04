import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
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

        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[0][0] = true;
        q.offerLast(new int[] {0, 0});
        while(!q.isEmpty()) {
            int[] cur = q.pollFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[0] + dr[d];
                int nC = cur[1] + dc[d];

                if (nR == N - 1 && nC == M - 1) {
                    System.out.print(1);
                    return;
                }

                if (0 <= nR && nR < N && 0 <= nC && nC < M && map[nR][nC] == 1 && !visited[nR][nC]) {
                    visited[nR][nC] = true;
                    q.offerLast(new int[] {nR, nC});
                }
            }
        }

        System.out.print(0);
    }
}