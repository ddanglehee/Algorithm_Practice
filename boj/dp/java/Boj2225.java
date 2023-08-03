import java.io.*;
import java.util.*;

public class Boj2225 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i == 1) {
                    dp[i][j] = dp[i][j - 1] + 1;
                } else if (j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 1000000000;
                }
            }
        }

        System.out.print(dp[n][k]);
    }
}
