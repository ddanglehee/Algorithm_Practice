class FarJump {

    private var answer: Long = 0

    fun solution(n: Int): Long {
        if (n in 1..2) return n.toLong();

        val dp = LongArray(n + 1)
        dp[1] = 1;
        dp[2] = 2;

        for (i in 3..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1234567
        }

        return dp[n]
    }
}