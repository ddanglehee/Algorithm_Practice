import java.util.Collections
import java.util.PriorityQueue

class Boj2841 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, p) = readLine().split(" ").map { it.toInt() }
        val info = Array(7) {
            PriorityQueue<Int>(Collections.reverseOrder())
        }

        var answer = 0
        repeat(n) {
            val (i, j) = readLine().split(" ").map { it.toInt() }
            val pq = info[i]

            while (pq.isNotEmpty() && j < pq.peek()) {
                pq.poll()
                answer++
            }

            if (pq.isEmpty() || pq.peek() < j) {
                pq.add(j)
                answer++
            }
        }

        print(answer)
    }
}