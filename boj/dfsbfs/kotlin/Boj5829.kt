class Boj5829 {

    fun main() = with(System.`in`.bufferedReader()) {

        var (n, m, k) = readLine().split(" ").map { it.toInt() }

        val portList = Array(n + 1) { Port() }
        for (number in 1..n) {
            val (left, right) = readLine().split(" ").map { it.toInt() }
            portList[number] = Port(left, right)
        }

        val directions = readLine().split(" ")

        val moveGraph = mutableMapOf<Int, Int>()
        // 특정 항구에서 시작해서 directions를 다 돌았을 때 도착지점 찾기
        for (number in 1..n) {
            var currentPort = number

            directions.forEach { direction ->
                when (direction) {
                    "L" -> {
                        currentPort = portList[currentPort].left
                    }
                    "R" -> {
                        currentPort = portList[currentPort].right
                    }
                }
            }

            moveGraph[number] = currentPort
        }

        val visitTime = IntArray(n + 1)
        var time = 1
        var currentPort = 1
        val cycle: Int
        val cycleStartPort: Int
        while (true) {
            if (visitTime[currentPort] != 0) {
                cycle = time - visitTime[currentPort]
                cycleStartPort = currentPort
                k -= (visitTime[currentPort] - 1)
                break
            }

            visitTime[currentPort] = time
            currentPort = moveGraph[currentPort]!!
            time++
        }

        k %= cycle
        currentPort = cycleStartPort
        while (0 < k) {
            currentPort = moveGraph[currentPort]!!
            k--
        }

        println(currentPort)
    }

    data class Port(val left: Int = -1, val right: Int = -1)
}