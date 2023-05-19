class Solution {

    var answer = Int.MAX_VALUE
    val dr = intArrayOf(0, -1, 0, 1)
    val dc = intArrayOf(-1, 0, 1, 0)

    fun solution(board: Array<IntArray>): Int {
        val n = board.size

        dfs(n, board, Array(n) { BooleanArray(n) }, 0, 0, 2, 0)
        dfs(n, board, Array(n) { BooleanArray(n) }, 0, 0, 3, 0)

        return answer
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