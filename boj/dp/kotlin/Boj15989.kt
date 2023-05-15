class Boj15989 {

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()
        val dp = Array(10001) { LongArray(4) }
        dp[1][1] = 1
        dp[2][1] = 1; dp[2][2] = 1
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1
        for (i in 4..10000) {
            for (k in 1..3) {
                for (j in 1..k) {
                    dp[i][k] += dp[i - k][j]
                }
            }
        }

        repeat(t) {
            val n = readLine().toInt()
            sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n")
        }

        print(sb)
    }
}