class Boj18111 {

    fun main() = with (System.`in`.bufferedReader()) {
        val (n, m, b) = readLine().split(" ").map{ it.toInt() }
        val map = Array(n) { IntArray(m) }

        for (i in 0 until n) {
            map[i] = readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        var time = Int.MAX_VALUE
        var height = 0
        for (h in 0..256) {
            var inventory = b
            var tmpTime = 0

            // 1. 땅 고르기 작업
            for (i in 0 until n) {
                for (j in 0 until m) {

                    // mid보다 크면 (map[i][j] - mid) * 2초 걸림, inventory += (map[i][j] - mid)
                    if (h < map[i][j]) {
                        tmpTime += (map[i][j] - h) * 2
                        inventory += (map[i][j] - h)
                    }
                    // mid보다 작으면 (mid - map[i][j])초 걸림, inventory -= (mid - map[i][j])
                    else {
                        tmpTime += (h - map[i][j])
                        inventory -= (h - map[i][j])
                    }
                }
            }

            // 2. 땅 고르기 작업이 끝났을 때 결과 계산
            if (0 <= inventory) {
                if (tmpTime <= time) {
                    time = tmpTime
                    height = h
                }
            }
        }

        println("$time $height")
    }
}