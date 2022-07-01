import kotlin.math.*

class FeatureDevelopment {
    fun solution(progresses: IntArray, speeds: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val days = mutableListOf<Int>()

        for (i in progresses.indices) {
            days.add(ceil(((100 - progresses[i]) / speeds[i].toDouble())).toInt())
        }

        var count = 0;
        var max = 0;
        for (i in 0 until days.size - 1) {
            count++
            if (max < days[i]) max = days[i]
            if (max < days[i + 1]) {
                answer.add(count)
                count = 0
            }
        }
        answer.add(++count)
        return answer.toIntArray()
    }
}