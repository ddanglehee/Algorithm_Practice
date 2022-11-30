class Boj1018 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        val board = Array(n) { "" }
        for (i in 0 until n) {
            board[i] = readLine()
        }

        var answer = 2500
        for (i in 0..(n - 8)) {
            for (j in 0..(m - 8)) {
                val count = recolorCount(board, i, j, 'W').coerceAtMost(recolorCount(board, i, j, 'B'))
                if (count < answer) answer = count
            }
        }

        println(answer)
    }

    fun recolorCount(board: Array<String>, row: Int, column: Int, firstColor: Char): Int {
        var count = 0

        for (i in row until row + 8) {
            for (j in column until column + 8) {
                val currentColor = board[i][j]
                val diff = (i - row) + (j - column)

                if ((diff % 2 == 0 && firstColor != currentColor) || (diff % 2 == 1 && firstColor == currentColor)) {
                    count++
                }
            }
        }

        return count
    }
}