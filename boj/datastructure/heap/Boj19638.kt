import java.util.*

class Boj19638 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, h, t) = readLine().split(" ").map { it.toInt() }

        val pq = PriorityQueue<Int>(Collections.reverseOrder())
        repeat(n) {
            pq.add(readLine().toInt())
        }

        if (pq.peek() < h) {
            println("YES")
            print(0)
            return
        }

        for (i in 1..t) {
            val max = pq.poll()
            pq.add(max / 2)

            if (max == 1 && h <= max) {
                println("NO")
                print(1)
                return
            } else if (pq.peek() < h) {
                println("YES")
                print(i)
                return
            }
        }

        println("NO")
        print(pq.peek())
    }
}