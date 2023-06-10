import java.util.PriorityQueue

class Boj1238 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m, x) = readLine().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val dist = Array(n + 1) { IntArray(n + 1) { 10000000 } }

        repeat(m) {
            val (s, e, d) = readLine().split(" ").map { it.toInt() }
            graph[s].add(e to d)
        }

        for (start in 1..n) {
            dijkstra(graph, start, dist)
        }

        var answer = 0
        for (i in 1..n) {
            answer = maxOf(answer, dist[i][x] + dist[x][i])
        }

        print(answer)
    }

    fun dijkstra(graph: Array<MutableList<Pair<Int, Int>>>, start: Int, dist: Array<IntArray>) {
        dist[start][start] = 0
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        pq.add(start to 0)

        while (pq.isNotEmpty()) {
            val (cur, d) = pq.poll()

            if (dist[start][cur] < d) continue

            graph[cur].forEach { (next, cost) ->
                val newDist = dist[start][cur] + cost
                if (newDist < dist[start][next]) {
                    dist[start][next] = newDist
                    pq.add(next to newDist)
                }
            }
        }
    }
}