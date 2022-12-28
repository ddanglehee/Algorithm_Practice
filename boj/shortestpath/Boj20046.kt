import java.util.PriorityQueue

class Boj20046 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val graph = mutableListOf<List<Int>>()
        repeat(n) {
            graph.add(readLine().split(" ").map { it.toInt() })
        }

        if (graph[0][0] == -1) {
            println(-1)
            return
        }

        val dx = listOf(-1, 0, 1, 0)
        val dy = listOf(0, 1, 0, -1)
        val visited = Array(n) { BooleanArray(m) }
        val priorityQueue = PriorityQueue<Road>(compareBy { it.cost })

        visited[0][0] = true
        priorityQueue.add(Road(0, 0, graph[0][0]))
        while(priorityQueue.isNotEmpty()) {
            val curRoad = priorityQueue.poll()

            if (curRoad.x == n - 1 && curRoad.y == m - 1) {
                println(curRoad.cost)
                return
            }

            for (i in 0 until 4) {
                val tmpX = curRoad.x + dx[i]
                val tmpY = curRoad.y + dy[i]
                if (tmpX in 0 until n && tmpY in 0 until m && graph[tmpX][tmpY] != -1 && !visited[tmpX][tmpY]) {
                    visited[tmpX][tmpY] = true
                    priorityQueue.add(Road(tmpX, tmpY, curRoad.cost + graph[tmpX][tmpY]))
                }
            }
        }
        println(-1)
    }

    data class Road(val x: Int, val y: Int, val cost: Int)
}