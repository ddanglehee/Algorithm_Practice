class Boj11657 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val INF = Long.MAX_VALUE

        val graph = List(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val dist = LongArray(n + 1) { INF }
        repeat(m) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }

            graph[a].add(b to c)
        }

        var negativeCycle = false
        dist[1] = 0
        for (i in 1..n) {
            for (from in 1..n) {
                if (dist[from] == INF) continue

                for (next in graph[from]) {
                    val to = next.first
                    val cost = next.second

                    if (dist[from] + cost < dist[to]) {
                        if (i == n) {
                            negativeCycle = true
                        }
                        dist[to] = dist[from] + cost
                    }
                }
            }
        }

        if (negativeCycle) {
            println(-1)
        } else {
            val sb = StringBuilder()
            for (i in 2..n) {
                if (dist[i] == INF) {
                    sb.append(-1).append("\n")
                } else {
                    sb.append(dist[i]).append("\n")
                }

            }
            println(sb)
        }
    }
}