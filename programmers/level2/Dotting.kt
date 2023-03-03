import kotlin.math.*

class Dotting {
    fun solution(k: Int, d: Int): Long {
        var answer: Long = 0

        for (x in 0..d step k) {
            val bMax = sqrt((d.toLong() * d - x.toLong() * x).toDouble()).toInt() / k + 1
            answer += bMax
        }

        return answer
    }
}