class Boj1010 {
    fun main() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        val dp = Array(31) {
            LongArray(31)
        }
        for (i in 1..30) {
            dp[i][0] = 1
        }

        for (i in 1..30) {
            for (j in 1..30) {
                if (i == j) {
                    dp[i][j] = 1
                } else if (j == 1) {
                    dp[i][j] = i.toLong()
                }else {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
                }
            }
        }

        val sb = StringBuilder()
        repeat(T) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            sb.append(dp[m][n]).append("\n")
        }

        print(sb)
    }
}