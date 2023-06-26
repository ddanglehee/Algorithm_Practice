class Boj2293 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val dp = IntArray(k + 1) // dp[i] : i원을 만드는 경우의 수
        dp[0] = 1

        repeat(n) {
            val coin = readLine().toInt()

            for (i in coin..k) {
                dp[i] += dp[i - coin]
            }
        }

        print(dp[k])
    }
}