class Boj1743 {

    val dr = intArrayOf(0, 0, -1, 1)
    val dc = intArrayOf(-1, 1, 0, 0)

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n) {
            IntArray(m)
        }

        repeat(k) {
            val (r, c) = readLine().split(" ").map { it.toInt() }
            graph[r - 1][c - 1] = 1
        }

        var answer = 0;
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (graph[i][j] == 1) {
                    answer = maxOf(answer, dfs(graph, i, j, n, m))
                }
            }
        }

        print(answer)
    }

    fun dfs(graph: Array<IntArray>, r: Int, c: Int, n: Int, m: Int): Int {
        graph[r][c] = 0

        var count = 1;
        for (d in 0 until 4) {
            val nR = r + dr[d]
            val nC = c + dc[d]

            if (nR < 0 || n <= nR || nC < 0 || m <= nC || graph[nR][nC] == 0) continue
            count += dfs(graph, nR, nC, n, m)
        }

        return count
    }
}