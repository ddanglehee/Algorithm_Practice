class Solution {

    var answer = Int.MAX_VALUE
    val dr = intArrayOf(0, -1, 0, 1)
    val dc = intArrayOf(-1, 0, 1, 0)

    fun solution(board: Array<IntArray>): Int {
        val n = board.size

        // dfs(n, board, Array(n) { BooleanArray(n) }, 0, 0, 2, 0)
        // dfs(n, board, Array(n) { BooleanArray(n) }, 0, 0, 3, 0)
        bfs(n, board, 0, 0, 0)
        bfs(n, board, 0, 0, 1)

        return answer
    }

    fun bfs(n: Int, board: Array<IntArray>, r: Int, c: Int, d: Int) {
        val queue = ArrayDeque<Triple<Pair<Int, Int>, Int, Int>>()
        queue.add(Triple(Pair(r, c), d, 0))
        val costMap = Array(n) { IntArray(n) }
        costMap[r][c] = 1

        while (queue.isNotEmpty()) {
            val (location, direction, cost) = queue.removeFirst()

            if (location.first == n - 1 && location.second == n - 1) {
                answer = minOf(answer, cost)
                continue
            }

            for (i in 0 until 4) {
                val nR = location.first + dr[i]
                val nC = location.second + dc[i]

                if (nR in 0 until n && nC in 0 until n && board[nR][nC] == 0) {
                    val addition = if (direction == i) 100 else 600
                    if (costMap[nR][nC] == 0) {
                        costMap[nR][nC] = cost + addition
                        queue.add(Triple(Pair(nR, nC), i, cost + addition))
                    } else if (cost + addition < costMap[nR][nC]) {
                        costMap[nR][nC] = cost + addition
                        queue.add(Triple(Pair(nR, nC), i, cost + addition))
                    }
                }
            }
        }
    }

    fun dfs(n: Int, board: Array<IntArray>, visited: Array<BooleanArray>, r: Int, c: Int, d: Int, cost: Int) {
        if (r == n - 1 && c == n - 1) {
            answer = minOf(answer, cost)
            return
        }

        visited[r][c] = true
        for (i in 0 until 4) {
            val nR = r + dr[(d + i) % 4]
            val nC = c + dc[(d + i) % 4]

            if (nR in 0 until n && nC in 0 until n && board[nR][nC] == 0 && !visited[nR][nC]) {
                val addition = if (i == 0) 100 else 600
                dfs(n, board, visited, nR, nC, (d + i) % 4, cost + addition)
            }
        }

        visited[r][c] = false
    }
}