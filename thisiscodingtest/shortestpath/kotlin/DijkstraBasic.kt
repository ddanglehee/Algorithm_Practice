class DijkstraBasic {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() } // n: 노드의 개수, m: 간선의 개수
        val start = br.readLine().toInt() // 시작점
        val graph = List(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val distance = MutableList(n + 1) { INF }

        repeat(m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            graph[a].add(b to c)
        }

        dijkstra(start, graph, distance)
    }

    // 다익스트라 알고리즘
    fun dijkstra(start: Int, graph: List<MutableList<Pair<Int, Int>>>, distance: MutableList<Int>) {
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        distance[start] = 0
        priorityQueue.offer(start to distance[start])

        while(priorityQueue.isNotEmpty()) {
            val (currentNode, dist) = priorityQueue.poll()

            if (distance[currentNode] < dist) continue // 현재 노드가 이미 처리된 적이 있는 노드라면 무시 (visited 대신)

            graph[currentNode].forEach { adjacentNode ->
                if (distance[currentNode] + adjacentNode.second < distance[adjacentNode.first]) {
                    distance[adjacentNode.first] = distance[currentNode] + adjacentNode.second
                    priorityQueue.offer(adjacentNode.first to distance[adjacentNode.second])
                }
            }
        }
    }

    companion object {
        const val INF = 987654321
    }
}