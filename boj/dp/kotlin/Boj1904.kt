class Boj1904 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n == 1) {
            print(1)
            return
        }

        val dp = LongArray(n + 1)
        dp[1] = 1; dp[2] = 2

        for (i in 3..n) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 15746
        }

        print(dp[n])
    }
}