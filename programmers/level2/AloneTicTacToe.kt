class AloneTicTacToe {
    fun solution(board: Array<String>): Int {
        var oCount = 0;
        var xCount = 0;

        board.forEach { row ->
            row.forEach { c ->
                if (c == 'O') {
                    oCount++
                } else if (c == 'X') {
                    xCount++
                }
            }
        }

        if (xCount + 1 < oCount || oCount < xCount || (xCount == oCount && isWinner('O', board)) || (xCount < oCount && isWinner('X', board)))  {
            return 0
        } else  {
            return 1
        }
    }

    private fun isWinner(c: Char, board: Array<String>): Boolean {
        // 행 검사
        for (i in 0 until 3) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][1] == c) return true;
        }
        // 열 검사
        for (i in 0 until 3) {
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[1][i] == c) return true;
        }
        // 대각선 검사
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == c) return true;
        if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[1][1] == c) return true;

        return false
    }
}