class DualPriorityQueue {
    fun solution(operations: Array<String>): IntArray {
        var answer = intArrayOf(0, 0)

        val queue = ArrayDeque<Int>()
        operations.forEach { operation ->
            val (command, number) = operation.split(" ")

            when (command) {
                "I" -> {
                    queue.add(number.toInt())
                    queue.sort()
                }
                "D" -> {
                    if (queue.isNotEmpty()) {
                        if (number.toInt() < 0) {
                            queue.removeFirstOrNull()
                        } else {
                            queue.removeLastOrNull()
                        }
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
}