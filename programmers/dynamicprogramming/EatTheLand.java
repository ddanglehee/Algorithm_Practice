class Solution {

    int solution(int[][] land) {
        int r = land.length;
        int c = 4;
        int[][] dp = new int[r][c];
        dp[0][0] = land[0][0]; dp[0][1] = land[0][1]; dp[0][2] = land[0][2]; dp[0][3] = land[0][3];

        for (int i = 1; i < r; i++) {
            for (int j = 0; j < 4; j++) {
                int max = 0;
                for (int k = 0; k < 4; k++) {
                    if (j == k) continue;
                    max = Math.max(max, dp[i - 1][k]);
                }
                dp[i][j] = max + land[i][j];
            }
        }


        return Math.max(dp[r - 1][0], Math.max(dp[r - 1][1], Math.max(dp[r - 1][2], dp[r - 1][3])));
    }
}