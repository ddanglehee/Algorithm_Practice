class Boj12865 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val value = IntArray(n + 1)
        val weight = IntArray(n + 1)

        for (i in 1..n) {
            val (w, v) = readLine().split(" ").map { it.toInt() }

            value[i] = v
            weight[i] = w
        }

        val dp = Array(n + 1) {
            IntArray(k + 1)
        }

        for (i in 1..n) {
            for (j in 1..k) {
                // 배낭에 무게 j의 여유가 있을 때, 1~i번째 물품들 중에 일부 또는 전부를 선택해서 얻을 수 있는 최대 가치
                if (0 <= j - weight[i]) dp[i][j] = maxOf(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i])
                else dp[i][j] = dp[i - 1][j]
            }
        }

        print(dp[n][k])
    }
}