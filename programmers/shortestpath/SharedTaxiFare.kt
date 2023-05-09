import java.util.PriorityQueue

class Solution {

    val max = 987654321
    lateinit var graph: Array<MutableList<Pair<Int, Int>>>
    lateinit var dist: Array<IntArray>
    lateinit var used: BooleanArray

    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        var answer: Int = max

        init(n, fares)

        dijkstra(n, s)
        for (i in 1..n) {
            if (!used[i]) dijkstra(n, i)

            val d = dist[s][i] + dist[i][a] + dist[i][b]
            if (d < 0) continue
            answer = minOf(answer, d)
        }

        return answer
    }

    fun dijkstra(n: Int, s: Int) {
        used[s] = true
        dist[s][s] = 0
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        pq.add(Pair(0, s))

        while (pq.isNotEmpty()) {
            val (d, cur) = pq.poll()

            if (dist[s][cur] < d) continue

            for ((next, cost) in graph[cur]) {
                val nCost = d + cost
                if (nCost < 0 || dist[s][next] < nCost) continue

                dist[s][next] = nCost
                pq.add(Pair(nCost, next))
            }
        }
    }

    fun init(n: Int, fares: Array<IntArray>) {
        graph = Array(n + 1) { mutableListOf() }
        fares.forEach { fare ->
            graph[fare[0]].add(fare[1] to fare[2])
            graph[fare[1]].add(fare[0] to fare[2])
        }
        dist = Array(n + 1) { IntArray(n + 1) { max } }
        used = BooleanArray(n + 1)
    }
}