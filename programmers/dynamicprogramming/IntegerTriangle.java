class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;

        if (n == 1) return triangle[0][0];

        int[][] dp = new int[n][n];
        dp[1][0] = triangle[0][0] + triangle[1][0];
        dp[1][1] = triangle[0][0] + triangle[1][1];

        for (int i = 2; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + triangle[i][0];
            dp[i][i] = dp[i - 1][i - 1] + triangle[i][i];
            for (int j = 1; j < i; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
            }
        }

        int answer = 0;
        for (int j = 0; j < n; j++) {
            answer = Math.max(answer, dp[n - 1][j]);
        }

        return answer;
    }
}