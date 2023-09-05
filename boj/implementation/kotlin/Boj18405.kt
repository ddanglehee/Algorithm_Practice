class Boj18405 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map{ it.toInt() }
        val graph = Array(n + 1) {
            IntArray(n + 1)
        }

        // 1. 입력 받아서 graph, virusInfo 초기화
        val virusInfo = Array<MutableList<IntArray>>(k + 1) {
            mutableListOf()
        }
        for (i in 1..n) {
            val input = readLine().split(" ").map { it.toInt() }

            for (j in 1..n) {
                val virus = input[j - 1]
                graph[i][j] = virus
                if (virus != 0) virusInfo[virus].add(intArrayOf(i, j))
            }
        }
        val (s, x, y) = readLine().split(" ").map { it.toInt() }

        // 2. s초 동안
        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)
        repeat(s) {
            // 2-1. 번호가 낮은 것부터
            for (i in 1..k) {
                val tmp = mutableListOf<IntArray>()
                for (location in virusInfo[i]) {
                    // 2-2. 상하좌우로 퍼진다.
                    for (d in 0 until 4) {
                        val nR = location[0] + dr[d]
                        val nC = location[1] + dc[d]

                        if (nR in 1..n && nC in 1..n && graph[nR][nC] == 0) {
                            tmp.add(intArrayOf(nR, nC))
                            graph[nR][nC] = i
                        }
                    }
                }
                virusInfo[i] = tmp
            }
        }

        // 3. s초 뒤에 (x, y)에 존재하는 바이러스 종류 출력
        print(graph[x][y])
    }
}