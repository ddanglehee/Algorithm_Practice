import java.util.*

class Delivery {

    fun solution(n: Int, roads: Array<IntArray>, k: Int): Int {
        var answer = 0

        val graph = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val times = IntArray(n + 1) { Int.MAX_VALUE }

        roads.forEach { road ->
            val (from, to, time) = road

            graph[from].add(Pair(to, time))
            graph[to].add(Pair(from, time ))
        }

        times[1] = 0
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        queue.offer(Pair(0, 1))

        while (queue.isNotEmpty()) {
            val (time, now) = queue.poll()

            if (times[now] < time) continue

            for (road in graph[now]) {
                val newTime = times[now] + road.second
                if (newTime < times[road.first]) {
                    times[road.first] = newTime
                    queue.offer(Pair(newTime, road.first))
                }
            }
        }

        answer += times.filter { it <= k }.size

        return answer
    }
}