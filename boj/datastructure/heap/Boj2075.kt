import java.util.PriorityQueue

class Boj2075 {
    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val graph = Array(n) {
            readLine().split(" ").map { it.toLong() }
        }

        val pq = PriorityQueue<Long>()
        for (row in graph) {
            for (i in row) {
                if (pq.size < n) {
                    pq.offer(i)
                } else {
                    if (pq.peek() < i) {
                        pq.poll()
                        pq.offer(i)
                    }
                }
            }
        }

        print(pq.peek())
    }
}