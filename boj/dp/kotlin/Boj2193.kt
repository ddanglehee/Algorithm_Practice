class Boj2193 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = Array(n+1) {
            LongArray(2)
        }
        dp[1][0] = 0; dp[1][1] = 1

        for (i in 2..n) {
            dp[i][0] = dp[i-1][0] + dp[i-1][1]
            dp[i][1] = dp[i-1][0]
        }

        print(dp[n][0] + dp[n][1])
    }
}