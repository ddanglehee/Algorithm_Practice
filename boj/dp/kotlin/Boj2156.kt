class Boj2156 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val dp = IntArray(n + 1)
        val wine = IntArray(n + 1)
        for (i in 1..n) {
            val amount = readLine().toInt()
            wine[i] = amount

            if (i <= 2) {
                dp[i] = dp[i - 1] + wine[i]
            } else {
                dp[i] = maxOf(dp[i - 1], maxOf(dp[i - 3] + wine[i - 1], dp[i - 2]) + wine[i])
            }
        }

        print(dp[n])
    }
}