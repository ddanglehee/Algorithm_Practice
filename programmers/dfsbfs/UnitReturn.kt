class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val answer = IntArray(sources.size)
        val graph = Array(n + 1) {
            mutableListOf<Int>()
        }
        roads.forEach { (a, b) ->
            graph[a].add(b)
            graph[b].add(a)
        }

        sources.forEachIndexed { idx, source ->
            answer[idx] = bfs(source, destination, graph)
        }

        return answer
    }

    fun bfs(start: Int, dest: Int, graph: Array<MutableList<Int>>): Int {
        val visited = BooleanArray(graph.size)
        val queue = ArrayDeque<Pair<Int, Int>>()
        visited[start] = true
        queue.add(start to 0)

        var result = -1
        while (queue.isNotEmpty()) {
            val (n, d) = queue.removeFirst()

            if (n == dest) {
                result = d
                break
            }

            graph[n].forEach { next ->
                if (!visited[next]) {
                    queue.add(next to d + 1)
                    visited[next] = true
                }
            }
        }

        return result
    }
}