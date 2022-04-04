import kotlin.math.min

class FutureCity {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val graph = List(n + 1) { MutableList(n + 1) { INF } }
        for (i in 0..n) {
            graph[i][i] = 0
        }
        repeat(m) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            graph[a][b] = 1
            graph[b][a] = 1
        }
        val (x, k) = br.readLine().split(" ").map { it.toInt() }

        for (l in 1..n) {
            for (i in 1..n) {
                for(j in 1..n) {
                    graph[i][j] = min(graph[i][j], graph[i][l] + graph[l][j])
                }
            }
        }

        val minTime = graph[1][k] + graph[k][x]
        if (INF <= minTime) {
            println(-1)
        } else {
            println(minTime)
        }
    }

    companion object {
        const val INF = 987654321
    }
}