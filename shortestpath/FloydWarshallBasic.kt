import kotlin.math.min

class FloydWarshall {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val m = br.readLine().toInt()
        val costTable = Array(n + 1) { Array(n + 1) { INF } }

        // 자기 자신으로 가는 cost를 0으로 초기화
        for(i in 1.. n + 1) {
            costTable[i][i] = 0
        }

        repeat(m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            costTable[a][b] = c
        }

        for (k in 1..n + 1) {
            for (a in 1..n + 1) {
                for (b in 1..n + 1) {
                    costTable[a][b] = min(costTable[a][b] , costTable[a][k] + costTable[k][b])
                }
            }
        }
    }

    companion object {
        private const val INF = 987654321
    }
}