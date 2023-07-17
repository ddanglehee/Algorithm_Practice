class Boj2644 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val (a, b) = readLine().split(" ").map { it.toInt() }
        val m = readLine().toInt()

        val graph = Array(n + 1) {
            mutableListOf<Int>()
        }
        repeat(m) {
            val (x, y) = readLine().split(" ").map { it.toInt() }
            graph[x].add(y)
            graph[y].add(x)
        }

        val visited = IntArray(n + 1) { -1 }
        dfs(a, 0, graph, visited)
        print(visited[b])
    }

    fun dfs(cur: Int, count: Int, graph: Array<MutableList<Int>>, visited: IntArray) {
        visited[cur] = count

        for (nxt in graph[cur]) {
            if (visited[nxt] != -1) continue
            dfs(nxt, count + 1, graph, visited)
        }
    }
}