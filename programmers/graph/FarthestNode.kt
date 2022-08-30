import java.util.*

class FarthestNode {

    fun solution(n: Int, edges: Array<IntArray>): Int {
        var answer = 0

        val graph = Array(n + 1) {
            mutableListOf<Int>()
        }
        val visited = BooleanArray(n + 1)
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { (-1) * it.second }) // first: 노드 번호, second: 거리

        // 1. graph 구성
        edges.forEach { edge ->
            val a = edge[0]
            val b = edge[1]

            graph[a].add(b)
            graph[b].add(a)
        }

        // 2. BFS
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(1 to 0)
        visited[1] = true

        while (queue.isNotEmpty()) {
            val (currentNode, distance) = queue.removeFirst()

            graph[currentNode].forEach { next ->
                if (!visited[next]) {
                    val nextPair = Pair(next, distance + 1)
                    pq.offer(nextPair)
                    queue.add(nextPair)
                    visited[next] = true
                }
            }
        }

        // 3. 1번 노드에서 가장 멀리 떨어진 노드의 개수 구하기
        val maxDistance = pq.peek().second
        while (true) {
            val distance = pq.poll().second
            if (distance == maxDistance) {
                answer++
            } else {
                break
            }
        }

        return answer
    }
}