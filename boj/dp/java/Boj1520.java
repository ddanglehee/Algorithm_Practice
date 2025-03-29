import java.util.*;
import java.io.*;

public class Boj1520 {

    static int m,n;
    static int[][] map;
    static int[][] count;
    static long answer = 0;
    static final int[] dr = {1, 0, -1, 0};
    static final int[] dc = {0, 1, 0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m][n];
        count = new int[m][n];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                count[i][j] = -1;
            }
        }

        dfs(0, 0);
        System.out.print(answer);
    }

    private static int dfs(int r, int c) {
        if (r == m - 1 && c == n - 1) {
            answer++;
            return 1;
        }

        count[r][c] = 0;
        for (int d = 0; d < 4; d++) {
            int nR = r + dr[d];
            int nC = c + dc[d];

            if (nR < 0 || m <= nR || nC < 0 || n <= nC
                    || map[r][c] <= map[nR][nC]
            ) continue;

            if (count[nR][nC] != -1) {
                answer += count[nR][nC];
                count[r][c] += count[nR][nC];
            } else {
                count[r][c] += dfs(nR, nC);
            }
        }

        return count[r][c];
    }
}
