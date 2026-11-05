import java.util.*;
import java.io.*;

public class Main {

    static int N, K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int k = Integer.parseInt(st.nextToken());

                if (k == 0) {
                    map[i][j] = -1;
                } else if (k == 2) {
                    map[i][j] = 0;
                    q.offerLast(new int[] {0, i, j});
                } else {
                    map[i][j] = -2;
                }
            }
        }

        bfs(q);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void bfs(ArrayDeque<int[]> q) {
        int[] dr = {0, -1, 0, 1};
        int[] dc = {-1, 0, 1, 0};
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();

            for (int d = 0; d < 4; d++) {
                int nR = cur[1] + dr[d];
                int nC = cur[2] + dc[d];

                if (0 <= nR && nR < N && 0 <= nC && nC < N && map[nR][nC] == -2) {
                    map[nR][nC] = cur[0] + 1;
                    q.offerLast(new int[] {cur[0] + 1, nR, nC});
                }
            }
        }
    }
}