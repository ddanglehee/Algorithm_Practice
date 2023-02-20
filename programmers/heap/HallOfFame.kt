import java.util.PriorityQueue

class HallOfFame {
    fun solution(k: Int, scores: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val hallOfFame = PriorityQueue<Int>()

        scores.forEach { score ->
            if (hallOfFame.size < k) {
                hallOfFame.offer(score)
            } else {
                var minScore = hallOfFame.peek()
                if (minScore < score) {
                    hallOfFame.poll()
                    hallOfFame.offer(score)
                }
            }
            answer.add(hallOfFame.peek())
        }
        return answer.toIntArray()
    }
}