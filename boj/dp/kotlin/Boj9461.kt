class Boj9461 {
    fun main() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        val dp = LongArray(101)
        dp[1] = 1; dp[2] = 1; dp[3] = 1
        for (i in 4..100) {
            dp[i] = dp[i - 2] + dp[i - 3]
        }

        val sb = StringBuilder()
        repeat(T) {
            val n = readLine().toInt()
            sb.append(dp[n]).append("\n")
        }

        print(sb)
    }
}