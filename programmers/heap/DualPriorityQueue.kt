import java.util.*

class DualPriorityQueue {
    fun solution(operations: Array<String>): IntArray {
        var answer = intArrayOf(0, 0)

        val queue = kotlin.collections.ArrayDeque<Int>()
        operations.forEach { operation ->
            val (command, number) = operation.split(" ")

            when (command) {
                "I" -> {
                    queue.add(number.toInt())
                    queue.sort()
                }
                "D" -> {
                    if (number.toInt() < 0) {
                        queue.removeFirstOrNull()
                    } else {
                        queue.removeLastOrNull()
                    }
                }

            }
        }

        if (queue.isNotEmpty()) {
            answer[0] = queue.removeLast()
            answer[1] = queue.removeFirst()
        }

        return answer
    }

    fun solution2(operations: Array<String>): IntArray {
        var answer = intArrayOf(0, 0)

        val maxHeap = PriorityQueue<Int>(Collections.reverseOrder())
        val minHeap = PriorityQueue<Int>()

        operations.forEach { operation ->
            val (command, number) = operation.split(" ")

            when (command) {
                "I" -> {
                    maxHeap.offer(number.toInt())
                    minHeap.offer(number.toInt())
                }
                "D" -> {
                    if (number.toInt() > 0) {
                        maxHeap.poll()?.let { minHeap.remove(it) }
                    } else {
                        minHeap.poll()?.let { maxHeap.remove(it) }
                    }
                }
            }
        }

        if (maxHeap.isNotEmpty()) {
            answer[0] = maxHeap.peek()
            answer[1] = minHeap.peek()
        }

        return answer
    }
}