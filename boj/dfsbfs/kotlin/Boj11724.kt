class Boj11724 {

    fun main() = with(System.`in`.bufferedReader()) {
        var answer = 0
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { mutableListOf<Int>() }

        repeat(m) {
            val (u, v) = readLine().split(" ").map { it.toInt() }
            graph[u].add(v)
            graph[v].add(u)
        }

        val visited = BooleanArray(n + 1)
        for (i in 1..n) {
            if (!visited[i]) {
                bfs(i, graph, visited)
                answer++
            }
        }

        println(answer)
    }

    fun bfs(n: Int, graph: Array<MutableList<Int>>, visited: BooleanArray) {
        val queue = ArrayDeque<Int>()
        queue.add(n)
        visited[n] = true

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            for (next in graph[current]) {
                if (!visited[next]) {
                    queue.add(next)
                    visited[next] = true
                }
            }
        }
    }
}