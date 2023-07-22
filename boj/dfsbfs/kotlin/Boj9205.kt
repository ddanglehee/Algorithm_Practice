import kotlin.math.abs

class Boj9205 {

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()

        repeat(t) {
            val n = readLine().toInt()
            val house = readLine().split(" ").map { it.toInt() }
            val convList = mutableListOf<List<Int>>()
            repeat(n) {
                convList.add(readLine().split(" ").map { it.toInt() })
            }
            val festival = readLine().split(" ").map { it.toInt() }
            bfs(house, convList, festival)
        }
    }

    fun bfs(start: List<Int>, convList: List<List<Int>>, festival: List<Int>) {
        val visited = BooleanArray(convList.size)
        val queue = ArrayDeque<List<Int>>()
        queue.add(start)

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            if (abs(x - festival[0]) + abs(y - festival[1]) <= 1000) {
                println("happy")
                return
            }

            for (i in convList.indices) {
                if (visited[i]) continue
                if (abs(x - convList[i][0]) + abs(y - convList[i][1]) <= 1000) {
                    visited[i] = true
                    queue.add(convList[i])
                }
            }
        }
        println("sad")
    }
}