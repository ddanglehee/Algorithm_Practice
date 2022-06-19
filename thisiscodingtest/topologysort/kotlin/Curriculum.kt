import kotlin.math.max

class Curriculum {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val indegree = Array(n + 1) { 0 }
        val graph = List(n + 1) { mutableListOf<Int>() }
        val time = Array(n + 1) { 0 }
        for (i in 1..n) {
            val tmp = br.readLine().split(" ").map { it.toInt() }.filter { it != -1 }
            time[i] = tmp[0]
            tmp.subList(1, tmp.lastIndex + 1).forEach {
                graph[it].add(i)
                indegree[i]++
            }
        }

        val queue = mutableListOf<Int>()
        for (i in 1..n) {
            if (indegree[i] == 0) {
                queue.add(i)
            }
        }

        val result = Array(n + 1) { time[it] }
        while (queue.isNotEmpty()) {
            val currentClass = queue.removeFirst()

            graph[currentClass].forEach {
                result[it] = max(result[it], result[currentClass] + time[it])
                indegree[it]--

                if (indegree[it]  == 0) {
                    queue.add(it)
                }
            }
        }

        for (i in 1..n) {
            println(result[i])
        }
    }
}