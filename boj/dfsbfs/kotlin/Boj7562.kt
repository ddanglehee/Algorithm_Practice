class Boj7562 {
    val dr = intArrayOf(-2, -1, 1, 2, 2, 1, -1, -2)
    val dc = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()

        val sb = StringBuilder()
        repeat(t) {
            val l = readLine().toInt()
            val start = readLine().split(" ").map { it.toInt() }
            val end = readLine().split(" ").map { it.toInt() }
            sb.append(bfs(l, start, end)).append("\n")
        }

        print(sb)
    }

    fun bfs(l: Int, start: List<Int>, end: List<Int>): Int {
        val visited = Array(l) {
            BooleanArray(l)
        }
        val queue = ArrayDeque<Pair<Int, IntArray>>()
        visited[start[0]][start[1]] = true
        queue.add(0 to intArrayOf(start[0], start[1]))

        while (queue.isNotEmpty()) {
            val (count, location) = queue.removeFirst()

            if (location[0] == end[0] && location[1] == end[1]) return count

            for (d in 0 until 8) {
                val nR = location[0] + dr[d]
                val nC = location[1] + dc[d]

                if (nR in 0 until l && nC in 0 until l && !visited[nR][nC]) {
                    visited[nR][nC] = true
                    queue.add(count + 1 to intArrayOf(nR, nC))
                }
            }
        }

        return 0
    }
}