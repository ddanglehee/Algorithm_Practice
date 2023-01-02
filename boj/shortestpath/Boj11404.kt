class Boj11404 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val m = readLine().toInt()

        val dist = Array(n + 1) {
            Array(n + 1) { 987654321 }
        }
        for (i in 1..n) {
            for (j in 1..n) {
                if (i == j) dist[i][j] = 0
            }
        }

        repeat(m) {
            val (a, b, c) = readLine().split(" ").map { it.toInt() }
            dist[a][b] = minOf(dist[a][b], c)
        }

        for (k in 1..n) {
            for (i in 1..n) {
                for (j in 1..n) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) dist[i][j] = dist[i][k] + dist[k][j]
                }
            }
        }

        val sb = StringBuilder()
        for (i in 1..n) {
            for (j in 1..n) {
                if (dist[i][j] != 987654321) {
                    sb.append(dist[i][j])
                } else {
                    sb.append(0)
                }
                sb.append(" ")
            }
            sb.append("\n")
        }
        println(sb)
    }
}