class Boj1189 {

    var answer = 0
    lateinit var visited: Array<BooleanArray>
    val dr = intArrayOf(0, 0, -1, 1)
    val dc = intArrayOf(-1, 1, 0, 0)

    fun main() = with(System.`in`.bufferedReader()) {
        val (r, c, k) = readLine().split(" ").map { it.toInt() }
        val graph = Array(r) {
            CharArray(c)
        }
        visited = Array(r) { BooleanArray(c) }

        for (i in 0 until r) {
            val input = readLine()
            for (j in 0 until c) {
                graph[i][j] = input[j]
            }
        }

        dfs(graph, r - 1, 0, k, 1, r, c)

        print(answer)
    }

    fun dfs(graph: Array<CharArray>, i: Int, j: Int, k: Int, dist: Int, r: Int, c: Int) {
        if (i == 0 && j == c - 1 && dist == k) {
            answer++
            return
        }

        visited[i][j] = true

        for (d in 0 until 4) {
            val nR = i + dr[d]
            val nC = j + dc[d]

            if (nR in 0 until r && nC in 0 until c && graph[nR][nC] != 'T' && !visited[nR][nC]) {
                dfs(graph, nR, nC, k, dist + 1, r, c)
            }
        }

        visited[i][j] = false
    }
}