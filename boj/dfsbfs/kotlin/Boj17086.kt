class Boj17086 {

    fun main() = with(System.`in`.bufferedReader()) {
        var answer = 0
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n) {
            readLine().split(" ").map { it.toInt() }
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                if (graph[i][j] == 0) {
                    answer = maxOf(bfs(graph, n, m, i, j), answer)
                }
            }
        }

        print(answer)
    }

    fun bfs(graph: Array<List<Int>>, n: Int, m: Int, i: Int, j: Int): Int {
        val dr = intArrayOf(-1, -1, -1, 0, 1, 1, 1, 0)
        val dc = intArrayOf(-1, 0, 1, 1, 1, 0, -1, -1)

        val visited = Array(n) {
            BooleanArray(m)
        }
        val queue = ArrayDeque<Triple<Int, Int, Int>>()
        visited[i][j] = true
        queue.addLast(Triple(i, j, 0))

        while (queue.isNotEmpty()) {
            val (r, c, d) = queue.removeFirst()

            if (graph[r][c] == 1) return d

            for (k in 0 until 8) {
                val nR = r + dr[k]
                val nC = c + dc[k]

                if (nR in 0 until n && nC in 0 until m && !visited[nR][nC]) {
                    queue.addLast(Triple(nR, nC, d + 1))
                    visited[nR][nC] = true
                }
            }
        }

        return 0
    }
}