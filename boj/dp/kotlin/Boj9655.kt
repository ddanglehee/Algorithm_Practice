class Boj9655 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        if (n % 2 == 0) {
            print("CY")
        } else {
            print("SK")
        }
    }

    fun main2() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dp = IntArray(1001)

        dp[1] = 1; dp[2] = 2; dp[3] = 1

        for (i in 4..n) {
            dp[i] = minOf(dp[i - 1], dp[i - 3]) + 1
        }

        if (dp[n] % 2 == 0) {
            print("CY")
        } else {
            print("SK")
        }
    }
}