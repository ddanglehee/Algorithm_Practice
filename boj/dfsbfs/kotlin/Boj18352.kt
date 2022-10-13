class Boj18352 {

    fun main() = with (System.`in`.bufferedReader()) {
        val (N, M, K, X) = readLine().split(" ").map { it.toInt() }
        val dists = IntArray(N + 1) { -1 }
        val graph = Array(N + 1) { mutableListOf<Int>() }

        repeat(M) {
            val (from, to) = readLine().split(" ").map { it.toInt() }
            graph[from].add(to)
        }

        val pq = ArrayDeque<Pair<Int, Int>>()
        dists[X] = 0
        pq.add(X to 0)

        while (pq.isNotEmpty()) {
            val (now, dist) = pq.removeFirst()

            for (next in graph[now]) {
                if (dists[next] == -1) {
                    val newDist = dist + 1
                    pq.add(next to newDist)
                    dists[next] = newDist
                }
            }
        }

        val stringBuilder = StringBuilder()
        dists.forEachIndexed { i, dist ->
            if (dist == K) {
                stringBuilder.append(i).append("\n")
            }
        }

        if (stringBuilder.isNotEmpty()) {
            println(stringBuilder)
        } else {
            println(-1)
        }
    }
}