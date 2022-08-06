import java.util.*;

class TruckCrossingTheBridge {
    fun solution(bridge_length: Int, weight: Int, truck_weights: IntArray): Int {
        var answer = 1
        var weightSum = 0
        var currentTruckIndex = 0
        val queue = ArrayDeque<Int>()

        while (currentTruckIndex < truck_weights.size) {
            // 지금 트럭이 다리를 건널 수 있으면 다리에 트럭 올리기
            if (weightSum + truck_weights[currentTruckIndex] <= weight) {
                queue.offerLast(currentTruckIndex)
                weightSum += truck_weights[currentTruckIndex++]
            } else {
                queue.offerLast(-1)
            }

            // 다리를 다 건넌 트럭 빼기
            if (queue.size == bridge_length) {
                val outIndex = queue.pollFirst()
                if (outIndex != -1) {
                    weightSum -= truck_weights[outIndex]
                }
            }

            answer++
        }
        return answer + bridge_length - 1
    }
}