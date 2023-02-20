class EscapeMaze {

    private var answer = 0

    fun solution(maps: Array<String>): Int {
        var start = Pair(-1, -1)
        var lever = Pair(-1, -1)
        var end = Pair(-1, -1)
        val graph = Array(maps.size) { i ->
            val arr = CharArray(maps[i].length)
            maps[i].forEachIndexed { j, c ->
                arr[j] = c
                if (c == 'S') {
                    start = Pair(i, j)
                } else if (c == 'L') {
                    lever = Pair(i, j)
                } else if (c == 'E') {
                    end = Pair(i, j)
                }
            }
            arr
        }

        return if (!bfs(graph, start, lever)) {
            -1
        } else {
            if (!bfs(graph, lever, end)) {
                -1
            } else {
                answer
            }
        }

    }

    fun bfs(graph: Array<CharArray>, start: Pair<Int, Int>, end: Pair<Int, Int>): Boolean {
        val dx = intArrayOf(0, 0, -1, 1); val dy = intArrayOf(-1, 1, 0, 0)
        val n = graph.size; val m = graph[0].size
        val visited = Array(n) { BooleanArray(m) }

        val queue = ArrayDeque<Info>()
        visited[start.first][start.second] = true
        queue.add(Info(start, 0))

        while (queue.isNotEmpty()) {
            val info = queue.removeFirst()
            val cur = info.cur

            if (cur.first == end.first && cur.second == end.second) {
                answer += info.depth
                return true
            }

            for (i in 0 until 4) {
                val tmpX = cur.first + dx[i]; val tmpY = cur.second + dy[i]
                if (tmpX in 0 until n && tmpY in 0 until m && graph[tmpX][tmpY] != 'X' && !visited[tmpX][tmpY]) {
                    queue.add(Info(Pair(tmpX, tmpY), info.depth + 1))
                    visited[tmpX][tmpY] = true
                }
            }
        }

        return false
    }
}

data class Info(val cur: Pair<Int, Int>, val depth: Int)