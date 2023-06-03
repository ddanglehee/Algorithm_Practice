class Boj9465 {

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()

        val sb = StringBuilder()
        for (i in 0 until t) {
            val n = readLine().toInt()
            val sticker = Array(2) {
                readLine().split(" ").map { it.toInt() }
            }
            val dp = Array(2) {
                IntArray(n)
            }

            if (n == 1) {
                sb.append(maxOf(sticker[0][0], sticker[1][0])).append("\n")
                continue
            } else if (n == 2) {
                sb.append(maxOf(sticker[1][0] + sticker[0][1], sticker[0][0] + sticker[1][1])).append("\n")
                continue
            }

            dp[0][0] = sticker[0][0]; dp[1][0] = sticker[1][0]
            dp[0][1] = sticker[1][0] + sticker[0][1]; dp[1][1] = sticker[0][0] + sticker[1][1]

            for (j in 2 until n) {
                dp[0][j] = maxOf(dp[1][j - 1], dp[1][j - 2]) + sticker[0][j]
                dp[1][j] = maxOf(dp[0][j - 1], dp[0][j - 2]) + sticker[1][j]
            }

            sb.append(maxOf(maxOf(dp[0][n - 1], dp[1][n - 1]), maxOf(dp[0][n - 2], dp[1][n - 2]))).append("\n")
        }

        print(sb)
    }
}