class Boj14940 {

    fun main() = with (System.`in`.bufferedReader()) {

        var startX: Int = 0; var startY: Int = 0
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val graph: Array<List<Int>> = Array(n) { i ->
            val array = readLine().split(" ").map { it.toInt() }
            array.forEachIndexed { j, number ->
                if (number == 2) {
                    startX = i; startY = j
                }
            }
            array
        }

        val answer = bfs(graph, startX, startY, n, m)
        val sb = StringBuilder()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (graph[i][j] == 0) {
                    sb.append(0).append(" ")
                } else {
                    sb.append(answer[i][j]).append(" ")
                }
            }
            sb.append("\n")
        }

        print(sb)
    }

    fun bfs(graph: Array<List<Int>>, x: Int, y: Int, n: Int, m: Int): Array<IntArray> {
        val dx = listOf(0, 0, -1, 1); val dy = listOf(-1, 1, 0, 0)

        val answer = Array(n) { IntArray(m) { -1 } }
        val queue = ArrayDeque<Triple<Int, Int, Int>>() // (좌표, depth)
        val visited = Array(n) { BooleanArray(m) }
        visited[x][y] = true
        queue.add(Triple(x, y, 0))

        while (queue.isNotEmpty()) {
            val (curX, curY, depth) = queue.removeFirst()
            answer[curX][curY] = depth

            for (i in 0 until 4) {
                val nextX = curX + dx[i]; val nextY = curY + dy[i]
                if (nextX in (0 until n) && nextY in (0 until m)) {
                    if (graph[nextX][nextY] != 0 && !visited[nextX][nextY]) {
                        queue.add(Triple(nextX, nextY, depth + 1))
                        visited[nextX][nextY] = true
                    }
                }
            }
        }

        return answer
    }
}