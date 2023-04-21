class Boj11727 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n == 1) {
            print(1)
            return
        }

        val dp = IntArray(n + 1)
        dp[1] = 1; dp[2] = 3
        for (i in 3..n) {
            dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10007
        }

        print(dp[n])
    }
}