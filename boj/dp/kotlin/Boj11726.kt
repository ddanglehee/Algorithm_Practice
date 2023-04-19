class Boj11726 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = LongArray(n + 1)

        if (n == 1) {
            print(1)
            return
        }

        dp[1] = 1; dp[2] = 2

        for (i in 3..n) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 10007
        }

        print(dp[n])
    }
}