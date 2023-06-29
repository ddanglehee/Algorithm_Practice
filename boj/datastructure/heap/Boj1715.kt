import java.util.*

class Boj1715 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val pq = PriorityQueue<Int>()

        repeat(n) {
            pq.add(readLine().toInt())
        }

        var answer = 0
        while (pq.size != 1) {
            val a = pq.poll()
            val b = pq.poll()

            answer += (a + b)
            pq.add(a + b)
        }

        print(answer)
    }
}