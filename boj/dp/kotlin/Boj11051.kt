class Boj11051 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val dp = Array(n + 1) {
            IntArray(n + 1)
        }
        dp[1][0] = 1; dp[1][1] = 1

        for (i in 2..n) {
            for (j in 0..n) {
                if (j == 0 || i == j) {
                    dp[i][j] = 1
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007
                }
            }
        }

        print(dp[n][k])
    }
}