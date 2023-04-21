class Boj2775 {
    fun main() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        val dp = Array(15) {
            IntArray(15)
        }
        for (j in 1..14){
            dp[0][j] = j
        }

        for (i in 1..14) {
            dp[i][1] = 1
            for (j in 2..14) {
                dp[i][j] = dp[i][j-1] + dp[i - 1][j]
            }
        }

        val sb = StringBuilder()
        repeat(T) {
            val k = readLine().toInt()
            val n = readLine().toInt()

            sb.append(dp[k][n]).append("\n")
        }

        print(sb)
    }
}