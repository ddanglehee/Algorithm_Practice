class NQueen {

    private val isUsed = BooleanArray(13)
    private val isUsedRightUpDiagonal = BooleanArray(25)
    private val isUsedLeftUpDiagonal = BooleanArray(25)
    private var answer = 0

    fun solution(n: Int): Int {
        backtracking(n, 0)
        return answer
    }

    fun backtracking(n: Int, row: Int) {
        if (row == n) {
            answer++
            return
        }

        for (column in 1..n) {
            if (isUsed[column] || isUsedRightUpDiagonal[row + column] || isUsedLeftUpDiagonal[n + row - column + 1]) continue
            isUsed[column] = true
            isUsedRightUpDiagonal[row + column] = true
            isUsedLeftUpDiagonal[n + row - column + 1] = true
            backtracking(n, row + 1)
            isUsed[column] = false
            isUsedRightUpDiagonal[row + column] = false
            isUsedLeftUpDiagonal[n + row - column + 1] = false
        }
    }
}