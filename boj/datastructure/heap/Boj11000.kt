import java.util.*

class Boj11000 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val pq1 = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        val pq2 = PriorityQueue<Int>()

        repeat(n) {
            val (start, end) = readLine().split(" ").map { it.toInt() }

            pq1.add(start to end)
        }

        pq2.add(0)
        while(pq1.isNotEmpty()) {
            val (start, end) = pq1.poll()

            if (pq2.peek() <= start) {
                pq2.poll()
            }
            pq2.add(end)
        }

        println(pq2.size)
    }
}