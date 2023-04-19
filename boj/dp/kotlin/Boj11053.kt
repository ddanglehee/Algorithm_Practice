class Boj11053 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = readLine().split(" ").map { it.toInt() }
        val dp = IntArray(n) { 1 }

        for (i in 1 until n) {
            for (j in 0 until i) {
                if (arr[j] < arr[i] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1
                }
            }
        }

        print(dp.maxOrNull())
    }
}