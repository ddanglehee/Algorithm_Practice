import java.util.PriorityQueue

class Boj15903 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val cardQueue = PriorityQueue(readLine().split(" ").map { it.toLong() })
        repeat(m) {
            val a = cardQueue.poll()
            val b = cardQueue.poll()

            val sum = a + b
            cardQueue.add(sum)
            cardQueue.add(sum)
        }

        val answer = cardQueue.fold(0L) { acc, i ->
            acc + i
        }

        print(answer)
    }
}