class Boj2583 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (m, n, k) = readLine().split(" ").map { it.toInt() }
        val visited = Array(m) {
            BooleanArray(n)
        }

        repeat(k) {
            val (x1, y1, x2, y2) = readLine().split(" ").map { it.toInt() }

            for (i in y1 until y2) {
                for (j in x1 until x2) {
                    visited[i][j] = true
                }
            }
        }

        var count = 0
        val answer = mutableListOf<Int>()
        while (true) {
            var flag = true
            for (i in 0 until m) {
                for (j in 0 until n) {
                    if (visited[i][j]) continue
                    flag = false
                    count++
                    answer.add(bfs(i to j, visited))
                }
            }

            if (flag) break
        }

        answer.sort()
        println(count)
        println(answer.joinToString(" "))
    }

    fun bfs(start: Pair<Int, Int>, visited: Array<BooleanArray>): Int {
        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)

        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[start.first][start.second] = true
        queue.add(start)

        var result = 1
        while (queue.isNotEmpty()) {
            val location = queue.removeFirst()

            for (d in 0 until 4) {
                val nR = location.first + dr[d]
                val nC = location.second + dc[d]

                if (nR in visited.indices && nC in visited[0].indices && !visited[nR][nC]) {
                    result++
                    visited[nR][nC] = true
                    queue.add(nR to nC)
                }
            }
        }

        return result
    }
}