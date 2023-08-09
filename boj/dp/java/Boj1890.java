import java.util.*;
import java.io.*;

public class Boj1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dr = {0, 1};
        int[] dc = {1, 0};
        long[][] dp = new long[n][n];
        dp[0][0] = 1;
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (dp[r][c] == 0 || graph[r][c] == 0) continue;

                for (int d = 0; d < 2; d++) {
                    int nR = r + dr[d] * graph[r][c];
                    int nC = c + dc[d] * graph[r][c];

                    if (nR < 0 || n <= nR || nC < 0 || n <= nC) continue;
                    dp[nR][nC] += dp[r][c];
                }
            }
        }

        System.out.print(dp[n-1][n-1]);
    }
}
