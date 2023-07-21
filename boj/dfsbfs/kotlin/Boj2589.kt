class Boj2589 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (r, c) = readLine().split(" ").map { it.toInt() }
        val graph = Array(r) { readLine() }
        val visited = Array(r) {
            BooleanArray(c)
        }

        var answer = 0
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (graph[i][j] == 'L') {
                    for (k in 0 until r) {
                        for (l in 0 until c) {
                            visited[k][l] = false
                        }
                    }
                    answer = maxOf(answer, bfs(i, j, visited, graph))
                }
            }
        }

        print(answer)
    }

    fun bfs(startR: Int, startC: Int, visited: Array<BooleanArray>, graph: Array<String>): Int {
        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)

        val queue = ArrayDeque<Pair<Int, Pair<Int, Int>>>()
        queue.add(0 to Pair(startR, startC))
        visited[startR][startC] = true

        var max = 0
        while(queue.isNotEmpty()) {
            val (dist, cur) = queue.removeFirst()

            max = maxOf(max, dist)

            for (d in 0 until 4) {
                val nR = cur.first + dr[d]
                val nC = cur.second + dc[d]

                if (nR in visited.indices && nC in visited[0].indices && graph[nR][nC] == 'L' && !visited[nR][nC]) {
                    visited[nR][nC] = true
                    queue.add(dist + 1 to Pair(nR, nC))
                }
            }
        }

        return max
    }
}