class Boj11052 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val p = readLine().split(" ").map { it.toInt() }
        val dp = IntArray(n + 1)
        dp[1] = p[0]

        for (i in 2..n) {
            dp[i] = p[i - 1]
            for (k in 1 until i) {
                if (i % k == 0) {
                    dp[i] = maxOf(dp[i], dp[k] * (i / k))
                } else {
                    dp[i] = maxOf(dp[i], dp[k] + dp[i - k])
                }
            }
        }

        print(dp[n])
    }
}