class Boj11057 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val dp = Array(n + 1) {
            IntArray(10)
        } // dp[i][j] : 수의 길이가 i일 때 마지막 숫자가 j 이하인 수의 개수

        for (i in 0..9) {
            dp[1][i] = 1
        }

        for (i in 2..n) {
            dp[i][0] = dp[i - 1][0]
            for (j in 1..9) {
                dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % 10007
            }
        }

        var answer = 0
        for (j in 0..9) {
            answer += dp[n][j]
        }
        print(answer % 10007)
    }
}