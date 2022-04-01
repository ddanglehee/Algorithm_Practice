class DijkstraBasic {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() } // n: 노드의 개수, m: 간선의 개수
        val start = br.readLine().toInt() // 시작점
        val graph = List(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val visited = MutableList(n + 1) { false }
        val distance = MutableList(n + 1) { INF }

        repeat(m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            graph[a].add(b to c)
        }

        dijkstra(start, graph, visited, distance)
    }

    // 다익스트라 알고리즘 : O(V^2)
    fun dijkstra(start: Int, graph: List<MutableList<Pair<Int, Int>>>, visited: MutableList<Boolean>, distance: MutableList<Int>) {
        distance[start] = 0
        visited[start] = true
        graph[start].forEach { adjacentNode ->
            distance[adjacentNode.first] = adjacentNode.second
        }

        repeat(graph.size - 1) {
            val currentNode = getSmallestNode(visited, distance)
            visited[currentNode] = true
            graph[currentNode].forEach { adjacentNode ->
                if (distance[currentNode] + adjacentNode.second < distance[adjacentNode.first]) {
                    distance[adjacentNode.first] = distance[currentNode] + adjacentNode.second
                }
            }
        }
    }

    // 방문하지 않은 노드 중에서 가장 최단 거리가 짧은 노드의 번호 반환
    private fun getSmallestNode(visited: List<Boolean>, distance: List<Int>): Int {
        var min = INF
        var minIndex = -1

        visited.forEachIndexed { index, visited ->
            if (!visited && distance[index] < min) {
                min = distance[index]
                minIndex = index
            }
        }
        return minIndex
    }
    companion object {
        const val INF = 987654321
    }
}