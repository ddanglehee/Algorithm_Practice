class Solution {
    public int solution(int[] money) {
        // dp[i] : i-1인덱스까지의 집을 털었을 때 훔칠 수 있는 돈의 최댓값
        int[] dp1 = new int[money.length + 1]; // 인덱스 0 집을 터는 경우
        int[] dp2 = new int[money.length + 1]; // 인덱스 0인 집은 안 터는 경우
        dp1[1] = money[0];

        for (int i = 1; i < money.length; i++) {
            dp2[i + 1] = Math.max(dp2[i - 1] + money[i], dp2[i]);
            dp1[i + 1] = Math.max(dp1[i - 1] + money[i], dp1[i]);
        }
        return Math.max(dp1[money.length - 1], dp2[money.length]);
    }
}