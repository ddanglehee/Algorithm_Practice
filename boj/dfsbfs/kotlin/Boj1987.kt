class Boj1987 {

    val dr = intArrayOf(0, 0, -1, 1)
    val dc = intArrayOf(-1, 1, 0, 0)
    var answer = 1

    fun main() = with(System.`in`.bufferedReader()) {
        val (r, c) = readLine().split(" ").map { it.toInt() }
        val board = Array<String>(r) {
            readLine()
        }
        val isUsed = BooleanArray(26)

        dfs(0, 0, 1, isUsed, board)

        print(answer)
    }

    fun dfs(r: Int, c: Int, count: Int, isUsed: BooleanArray, board: Array<String>) {
        isUsed[board[r][c] - 'A'] = true
        answer = maxOf(answer, count)

        for (d in 0 until 4) {
            val nR = r + dr[d]
            val nC = c + dc[d]

            if (nR in board.indices && nC in 0 until board[0].length && !isUsed[board[nR][nC] - 'A']) {
                dfs(nR, nC, count + 1, isUsed, board)
            }
        }

        isUsed[board[r][c] - 'A'] = false
    }
}