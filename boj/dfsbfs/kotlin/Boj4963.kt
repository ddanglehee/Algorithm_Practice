class Boj4963 {

    fun main() = with(System.`in`.bufferedReader()) {

        val sb = StringBuilder()
        while (true) {
            val (w, h) = readLine().split(" ").map{ it.toInt() }
            if (w == 0 && h == 0) break

            val graph = Array(h) {
                readLine().split(" ").map { it.toInt() }.toIntArray()
            }

            var answer = 0
            for (i in 0 until h) {
                for (j in 0 until w) {
                    if (graph[i][j] == 1) {
                        answer++
                        bfs(graph, i to j)
                    }
                }
            }

            sb.append(answer).append("\n")
        }

        print(sb)
    }

    fun bfs(graph: Array<IntArray>, start: Pair<Int, Int>) {
        val dr = intArrayOf(-1, -1, -1, 0, 0, 1, 1, 1)
        val dc = intArrayOf(-1, 0, 1, -1, 1, -1, 0, 1)

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(start)

        while (queue.isNotEmpty()) {
            val location = queue.removeFirst()

            for (d in 0 until 8) {
                val nR = location.first + dr[d]
                val nC = location.second + dc[d]

                if (nR in graph.indices && nC in graph[0].indices && graph[nR][nC] == 1) {
                    graph[nR][nC] = 0
                    queue.add(nR to nC)
                }
            }
        }
    }
}