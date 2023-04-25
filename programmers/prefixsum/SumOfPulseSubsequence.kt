class Solution {
    fun solution(sequence: IntArray): Long {
        var answer: Long = 0

        // 초기화
        val sum1 = LongArray(sequence.size + 1)
        val sum2 = LongArray(sequence.size + 1)
        for (i in sequence.indices) {
            if (i % 2 == 0) {
                sum1[i + 1] = sum1[i] + sequence[i]
                sum2[i + 1] = sum2[i] - sequence[i]
            } else {
                sum1[i + 1] = sum1[i] - sequence[i]
                sum2[i + 1] = sum2[i] + sequence[i]
            }
        }

        val maxIndex1 = sum1.indexOf(sum1.maxOrNull()!!)
        var minIndex1 = 0
        for (i in 1 until maxIndex1) {
            if (sum1[i] < sum1[minIndex1]) {
                minIndex1 = i
            }
        }

        val maxIndex2 = sum2.indexOf(sum2.maxOrNull()!!)
        var minIndex2 = 0
        for (i in 1 until maxIndex2) {
            if (sum2[i] < sum2[minIndex2]) {
                minIndex2 = i
            }
        }

        return maxOf(sum1[maxIndex1] - sum1[minIndex1], sum2[maxIndex2] - sum2[minIndex2])
    }
}