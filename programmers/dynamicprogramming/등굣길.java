import java.util.*;

class Solution {

    private int[][] dp;

    public int solution(int m, int n, int[][] puddles) {
        init(n, m, puddles);

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (dp[i][j] == -1) continue;

                if (dp[i - 1][j] != -1) {
                    dp[i][j] += dp[i - 1][j] % 1000000007;
                }

                if (dp[i][j - 1] != -1) {
                    dp[i][j] += dp[i][j - 1] % 1000000007;
                }
            }
        }

        return dp[n][m] % 1000000007;
    }

    private void init(int n, int m, int[][] puddles) {
        dp = new int[n + 1][m + 1];

        for (int i = 0; i < puddles.length; i++) {
            dp[puddles[i][1]][puddles[i][0]] = -1;
        }
    }
}