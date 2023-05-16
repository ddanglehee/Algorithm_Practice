import java.util.PriorityQueue

class Boj1146 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (N, D) = readLine().split(" ").map { it.toInt() }
        val dist = IntArray(D + 1) { 987654321 }
        val graph = Array(D + 1) { mutableListOf<Pair<Int, Int>>() }

        for (i in 0 until D) {
            graph[i].add(Pair(i + 1, 1))
        }
        repeat(N) {
            val (start, end, d) = readLine().split(" ").map { it.toInt() }
            if (start < D) graph[start].add(Pair(end, d))
        }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        pq.offer(Pair(0, 0))
        dist[0] = 0

        while (pq.isNotEmpty()) {
            val (d, cur) = pq.poll()

            if (dist[cur] < d) continue

            graph[cur].forEach { (next, cost) ->
                val totalCost = dist[cur] + cost

                if (next <= D && totalCost < dist[next]) {
                    dist[next] = totalCost
                    pq.offer(Pair(dist[next], next))
                }
            }
        }

        print(dist[D])
    }
}