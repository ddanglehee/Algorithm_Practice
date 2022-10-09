class Boj1912 {

    fun main() = with (System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = readLine().split(" ").map { it.toInt() }
        val dp = Array(n) { IntArray(2) }

        dp[0][0] = 0; dp[0][1] = input[0]
        for (i in 1 until n) {
            dp[i][0] = maxOf(dp[i - 1][0], dp[i - 1][1])
            dp[i][1] = maxOf(dp[i - 1][1] + input[i], input[i])
        }

        if (dp[n - 1][0] == 0 && dp[n - 1][1] == input[n - 1]) { // 한 개도 선택되지 않은 경우
            println(input.maxOrNull())
        } else {
            println(maxOf(dp[n - 1][0], dp[n - 1][1]))
        }
    }
}