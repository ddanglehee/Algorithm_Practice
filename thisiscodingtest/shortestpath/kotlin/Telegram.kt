import java.util.PriorityQueue
import kotlin.math.max

class Telegram {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m, c) = br.readLine().split(" ").map { it.toInt() }
        val graph = List(n + 1) {
            mutableListOf<Pair<Int, Int>>()
        }
        val time = Array(n + 1) { INF }
        repeat(m) {
            val (x, y, z) = br.readLine().split(" ").map { it.toInt() }
            graph[x].add(y to z)
        }

        // dijkstra
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
        time[c] = 0

        priorityQueue.offer(c to time[c])
        while(priorityQueue.isNotEmpty()) {
            val currentCity = priorityQueue.poll()

            if (time[currentCity.first] < currentCity.second) continue

            graph[currentCity.first].forEach { adjacentCity ->
                if (adjacentCity.second + currentCity.second < time[adjacentCity.first]) {
                    time[adjacentCity.first] = adjacentCity.second + currentCity.second
                    priorityQueue.offer(adjacentCity.first to time[adjacentCity.first])
                }
            }
        }

        // 메시지를 받게 되는 도시의 개수, 도시들이 모두 메시지를 받는 데까지 걸리는 시간 구하기
        var totalTime = 0
        var cityCount = 0
        time.forEach {
            if (it < INF) {
                cityCount++
                totalTime = max(totalTime, it)
            }
        }

        println("${cityCount - 1} $totalTime")
    }

    companion object {
        const val INF = 987654321
    }
}