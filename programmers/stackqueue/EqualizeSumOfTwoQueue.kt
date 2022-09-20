class EqualizeSumOfTwoQueue {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        val q1 = ArrayDeque(queue1.map { it.toLong() })
        val q2 = ArrayDeque(queue2.map { it.toLong() })
        var sum1: Long = q1.sum()
        var sum2: Long = q2.sum()

        while (sum1 != sum2 && answer <= queue1.size * 4) {
            // 1. sum이 큰 쪽을 pop하고 반대쪽에 insert
            if (sum1 > sum2) {
                val popped = q1.removeFirst()
                sum1 -= popped
                q2.addLast(popped)
                sum2 += popped
            } else {
                val popped = q2.removeFirst()
                sum2 -= popped
                q1.addLast(popped)
                sum1 += popped
            }
            answer++
        }

        return if (queue1.size * 4 < answer) -1 else answer
    }
}