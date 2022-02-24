import java.util.*

class DfsBfsBasic {
    fun dfs(graph: List<List<Int>>, v: Int, visited: MutableList<Boolean>) {
        visited[v] = true

        for (i in graph[v]) {
            if (!visited[i]) dfs(graph, v, visited)
        }
    }

    fun bfs(graph: List<List<Int>>, start: Int, visited: MutableList<Boolean>) {
        val queue = LinkedList<Int>()

        queue.offer(start)
        visited[start] = true

        while(queue.isNotEmpty()) {
            val current = queue.poll()

            for (i in graph[current]) {
                if (!visited[i]) {
                    queue.offer(i)
                    visited[i] = true // 여기서 방문처리를 해주어야 큐에 중복으로 들어갈 일이 안 생긴다.
                }
            }
        }
    }
}