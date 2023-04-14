import java.util.*

class Solution {

    fun solution(n: Int, k: Int, enemy: IntArray): Int {
        var answer = minOf(k, enemy.size)
        var remainN = n
        val mujeok = PriorityQueue<Int>()

        for (i in 0 until minOf(k, enemy.size)) {
            mujeok.offer(enemy[i])
        }

        for (i in k until enemy.size) {
            if (mujeok.peek() < enemy[i]) {
                remainN -= mujeok.poll()
                mujeok.offer(enemy[i])
            } else {
                remainN -= enemy[i]
            }

            if (remainN < 0) break

            answer = i + 1
        }

        return answer
    }
}