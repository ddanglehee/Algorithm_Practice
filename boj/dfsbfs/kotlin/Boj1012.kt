class Boj1012 {

    val dy = intArrayOf(0, 0, -1, 1)
    val dx = intArrayOf(-1, 1, 0, 0)

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()

        repeat(t) {
            var answer = 0
            val (m, n, k) = readLine().split(" ").map { it.toInt() }
            val graph = Array(n) {
                IntArray(m)
            }
            val visited = Array(n) {
                BooleanArray(m)
            }
            val list = mutableListOf<Pair<Int, Int>>()

            repeat(k) {
                val (x, y) = readLine().split(" ").map { it.toInt() }
                graph[y][x] = 1
                list.add(y to x)
            }

            list.forEach { (y, x) ->
                if (!visited[y][x]) {
                    answer++
                    dfs(graph, visited, n, m, x, y)
                }
            }

            sb.append(answer).append("\n")
        }

        print(sb)
    }

    fun dfs(graph: Array<IntArray>, visited: Array<BooleanArray>, n: Int, m: Int, x: Int, y:Int) {
        visited[y][x] = true

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx in 0 until m && ny in 0 until n && graph[y][x] == 1 && !visited[ny][nx]) {
                dfs(graph, visited, n, m, nx, ny)
            }
        }
    }
}