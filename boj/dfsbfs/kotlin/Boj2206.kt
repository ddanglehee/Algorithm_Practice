class Boj2206 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val graph = Array<String>(n) {
            readLine()
        }

        val visited = Array(n) {
            Array(m) {
                BooleanArray(2)
            }
        }
        val queue = ArrayDeque<Triple<Pair<Int, Int>, Int, Int>>()
        visited[0][0][0] = true
        queue.add(Triple(Pair(0, 0), 1, 0))

        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)
        var answer = 1000001
        while (queue.isNotEmpty()) {
            val (location, dist, isBroken) = queue.removeFirst()

            if (location.first == n - 1 && location.second == m - 1) {
                answer = dist
                break
            }

            for (d in 0 until 4) {
                val nR = location.first + dr[d]
                val nC = location.second + dc[d]

                if (nR in 0 until n && nC in 0 until m && !visited[nR][nC][isBroken]) {
                    if (graph[nR][nC] == '0') {
                        visited[nR][nC][isBroken] = true
                        queue.add(Triple(Pair(nR, nC), dist + 1, isBroken))
                    } else if (graph[nR][nC] == '1' && isBroken == 0) {
                        visited[nR][nC][1] = true
                        queue.add(Triple(Pair(nR, nC), dist + 1, 1))
                    }
                }
            }
        }

        if (answer == 1000001) print(-1) else print(answer)
    }
}