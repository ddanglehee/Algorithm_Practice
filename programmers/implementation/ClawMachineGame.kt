class ClawMachineGame {

    fun solution(board: Array<IntArray>, moves: IntArray): Int {
        var answer = 0

        val dollStack = Array(board.size + 1) { mutableListOf<Int>() }
        for (i in board.size - 1 downTo 0) {
            for (j in board[i].indices) {
                if (board[i][j] != 0) {
                    dollStack[j + 1].add(board[i][j])
                }
            }
        }

        val basket = mutableListOf<Int>()
        moves.forEach { move ->
            if (dollStack[move].isNotEmpty()) {
                val doll = dollStack[move].removeLast()

                if (basket.isNotEmpty() && basket.last() == doll) {
                    basket.removeLast()
                    answer += 2
                } else {
                    basket.add(doll)
                }
            }
        }

        return answer
    }
}