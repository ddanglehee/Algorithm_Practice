class Boj19538 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val believeTime = IntArray(n + 1) { -1 }
        val adjacentList = List(n + 1) { mutableListOf<Int>() }

        for (i in 1..n) {
            val input = readLine().split(" ").map { it.toInt() }
            for (adjacent in input) {
                if (adjacent != 0) {
                    adjacentList[i].add(adjacent)
                }
            }
        }

        val m = readLine().toInt()
        val distributors = readLine().split(" ").map { it.toInt() }

        val queue = ArrayDeque<Pair<Int, Int>>()
        for (distributor in distributors) {
            queue.add(distributor to 0)
        }

        while (queue.isNotEmpty()) {
            val (distributor, time) = queue.removeFirst()
            if (believeTime[distributor] != -1) continue

            believeTime[distributor] = time

            for (i in adjacentList[distributor].indices) {
                val adjacent = adjacentList[distributor][i]
                if (believeTime[adjacent] != -1) continue

                var believerCount = 0
                val adjacentCount = adjacentList[adjacent].size

                adjacentList[adjacent].forEach {
                    if (believeTime[it] != -1) {
                        believerCount++
                    }
                }

                if ((adjacentCount + 1) / 2 <= believerCount) {
                    queue.add(adjacent to time + 1)
                }
            }
        }

        val sb = StringBuilder()
        for (i in 1..n) {
            sb.append(believeTime[i]).append(" ")
        }
        println(sb)
    }
}