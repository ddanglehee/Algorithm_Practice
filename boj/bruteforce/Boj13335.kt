import java.util.*

class Boj13335 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, w, l) = readLine().split(" ").map { it.toInt() }
        val trucks = readLine().split(" ").map { it.toInt() }
        val bridge = ArrayDeque<Int>()

        var time = 0
        var currentWeight = 0
        var nextIndex = 0 // 다음 트럭의 인덱스
        while (nextIndex < n) {
            time++
            if (bridge.size == w) {
                currentWeight -= bridge.removeFirst()
            }
            if (currentWeight + trucks[nextIndex] <= l) {
                currentWeight += trucks[nextIndex]
                bridge.add(trucks[nextIndex++])
            } else {
                bridge.add(0)
            }
        }

        time += w
        print(time)
    }
}