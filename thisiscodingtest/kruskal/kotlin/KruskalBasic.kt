import java.util.PriorityQueue

class KruskalBasic {

    private lateinit var parent: Array<Int>

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        parent = Array(v + 1) { it }
        val priorityQueue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        repeat(e) {
            val (a, b, cost) = br.readLine().split(" ").map { it.toInt() }
            priorityQueue.offer(Triple(cost, a, b))
        }
        var minCost = 0

        while (priorityQueue.isNotEmpty()) {
            val (cost, a, b) = priorityQueue.poll()

            if (parent[a] != parent[b]) {
                unionParent(a, b)
                minCost += cost
            }
        }

        println(minCost)
    }

    private fun findParent(x: Int): Int {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x])
        }

        return parent[x]
    }

    private fun unionParent(a: Int, b: Int) {
        val aParent = findParent(a)
        val bParent = findParent(b)

        if (aParent < bParent) {
            parent[bParent] = aParent
        } else if (bParent < aParent) {
            parent[aParent] = bParent
        }
    }
}