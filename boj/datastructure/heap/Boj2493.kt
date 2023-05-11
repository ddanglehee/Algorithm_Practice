import java.util.PriorityQueue

class Boj2493 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val towers = readLine().split(" ").map { it.toInt() }
        val answer = IntArray(n)

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        pq.offer(towers.last() to towers.lastIndex)
        for (i in towers.lastIndex - 1 downTo 0) {
            val height = towers[i]

            while (pq.isNotEmpty() && pq.peek().first <= height) {
                val (h, num) = pq.poll()
                answer[num] = i + 1
            }

            pq.offer(height to i)
        }

        print(answer.joinToString(" "))
    }
}