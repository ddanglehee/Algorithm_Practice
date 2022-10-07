class Boj1149 {

    fun main() = with (System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val rCost = IntArray(n + 1)
        val gCost = IntArray(n + 1)
        val bCost = IntArray(n + 1)

        val dp = Array(n + 1) { IntArray(3) }

        for (i in 1..n) {
            val (r, g, b) = readLine().split(" ").map { it.toInt() }

            rCost[i] = r; gCost[i] = g; bCost[i] = b
        }

        dp[1][0] = rCost[1]; dp[1][1] = gCost[1]; dp[1][2] = bCost[1]
        for (i in 2..n) {
            for (j in 0..2) {
                dp[i][j] = when (j) {
                    0 -> {
                        minOf(dp[i - 1][1], dp[i - 1][2]) + rCost[i]
                    }
                    1 -> {
                        minOf(dp[i - 1][0], dp[i - 1][2]) + gCost[i]
                    }
                    else -> {
                        minOf(dp[i - 1][0], dp[i - 1][1]) + bCost[i]
                    }
                }
            }
        }

        println(minOf(dp[n][0], minOf(dp[n][1], dp[n][2])))
    }
}