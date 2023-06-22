class Boj16234 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, l, r) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }
        val visited = Array(n) { BooleanArray(n) }

        var answer = 0
        var flag: Boolean
        while (true) {
            flag = false
            clearVisited(visited)
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (visited[i][j]) continue
                    if (bfs(graph, visited, i to j, l, r)) flag = true
                }
            }
            if (!flag) break
            answer++
        }

        print(answer)
    }

    fun bfs(graph: Array<IntArray>, visited: Array<BooleanArray>, start: Pair<Int, Int>, l: Int, r: Int): Boolean {
        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)
        val unionList = mutableListOf(start)
        var unionPopulation = graph[start.first][start.second]
        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[start.first][start.second] = true
        queue.add(start)

        while (queue.isNotEmpty()) {
            val location = queue.removeFirst()

            for (d in 0 until 4) {
                val nR = location.first + dr[d]
                val nC = location.second + dc[d]

                if (nR in graph.indices && nC in graph.indices && !visited[nR][nC]
                    && Math.abs(graph[location.first][location.second] - graph[nR][nC]) in l..r) {
                    visited[nR][nC] = true
                    unionList.add(nR to nC)
                    unionPopulation += graph[nR][nC]
                    queue.add(nR to nC)
                }
            }
        }

        for (country in unionList) {
            graph[country.first][country.second] = unionPopulation / unionList.size
        }

        return unionList.size > 1
    }

    fun clearVisited(visited: Array<BooleanArray>) {
        for (i in visited.indices) {
            for (j in visited.indices) {
                visited[i][j] = false
            }
        }
    }
}