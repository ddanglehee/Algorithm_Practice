class Boj2579 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val arr = IntArray(n + 1) // i번째 계단의 점수
        val dp = IntArray(n + 1) // i번째 계단을 밟았을 때 점수의 최댓값

        for (i in 1..n) {
            val score = readLine().toInt()
            arr[i] = score
            if (i <= 2) {
                dp[i] = dp[i - 1] + score
            } else {
                // [(i-3) -> (i-1) -> i]과정과 [(i-2) -> (i)]과정 중 점수 높은 거 선택
                dp[i] = maxOf(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i]
            }
        }

        print(dp[n]) // 마지막 도착 계단은 반드시 밟아야 한다.
    }
}