class Boj2468 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val graph = Array(n) {
            readLine().split(" ").map{ it.toInt() }
        }

        var answer = 0
        var rainHeight = 0

        while (true) {
            var count = 0
            val visited = Array(n) { BooleanArray(n) }

            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (rainHeight < graph[i][j] && !visited[i][j]) {
                        count++
                        bfs(i, j, graph, visited, rainHeight, n)
                    }
                }
            }

            if (count == 0) break
            answer = maxOf(answer, count)
            rainHeight++
        }

        print(answer)
    }

    fun bfs(r: Int, c: Int, graph: Array<List<Int>>, visited: Array<BooleanArray>, rainHeight: Int, n: Int) {
        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)
        visited[r][c] = true
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(r, c))

        while (queue.isNotEmpty()) {
            val (i, j) = queue.removeFirst()

            for (d in 0 until 4) {
                val nR = i + dr[d]
                val nC = j + dc[d]

                if (nR in 0 until n && nC in 0 until n && rainHeight < graph[nR][nC] && !visited[nR][nC]) {
                    visited[nR][nC] = true
                    queue.add(Pair(nR, nC))
                }
            }
        }
    }
}