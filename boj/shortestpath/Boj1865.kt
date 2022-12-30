class Boj1865 {

    fun main() = with(System.`in`.bufferedReader()) {
        val tc = readLine().toInt()

        val sb = StringBuilder()
        val dist = LongArray(501)
        repeat(tc) {
            val (n, m, w) = readLine().split(" ").map { it.toInt() }

            val edges = mutableListOf<Triple<Int, Int, Int>>()
            repeat(m) {
                val (s, e, t) = readLine().split(" ").map { it.toInt() }

                edges.add(Triple(s, e, t))
                edges.add(Triple(e, s, t))
            }
            repeat(w) {
                val (s, e, t) = readLine().split(" ").map { it.toInt() }

                edges.add(Triple(s, e, (t * -1)))
            }

            var negativeCycle = false
            dist[1] = 0
            for (i in 1..n) {
                for (edge in edges) {
                    val from = edge.first
                    val to = edge.second
                    val cost = edge.third

                    if (dist[from] + cost < dist[to]) {
                        if (i == n) {
                            negativeCycle = true
                            break
                        }
                        dist[to] = dist[from] + cost
                    }
                }
                if (negativeCycle) break
            }

            if (negativeCycle) {
                sb.append("YES").append("\n")
            } else {
                sb.append("NO").append("\n")
            }
        }
        print(sb)
    }
}