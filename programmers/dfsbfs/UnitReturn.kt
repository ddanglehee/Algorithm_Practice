class Solution {
    // 정답 풀이 (destination에서 출발하는 걸 왜 생각 못했을까ㅠ!!)
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        val answer = IntArray(sources.size)
        val graph = Array(n + 1) {
            mutableListOf<Int>()
        }
        val dist = IntArray(n + 1) { -1 }

        roads.forEach { (a, b) ->
            graph[a].add(b)
            graph[b].add(a)
        }

        bfs(destination, graph, dist)

        for (i in sources.indices) {
            answer[i] = dist[sources[i]]
        }

        return answer
    }

    fun bfs(start: Int, graph: Array<MutableList<Int>>, dist: IntArray) {
        val queue = ArrayDeque<Pair<Int, Int>>()
        dist[start] = 0
        queue.add(start to 0)

        while (queue.isNotEmpty()) {
            val (node, d) = queue.removeFirst()

            graph[node].forEach { next ->
                if (dist[next] == -1) {
                    dist[next] = d + 1
                    queue.add(next to d + 1)
                }
            }
        }
    }

    // 시간 초과 풀이
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