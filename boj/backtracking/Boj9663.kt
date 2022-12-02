class Boj9663 {

    var answer = 0
    val columnUsed = BooleanArray(14)
    val leftTopUsed = BooleanArray(27) // (i - j)가 같음
    val rightTopUsed = BooleanArray(27) // (i + j)가 같음

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        setQueen(n, 0)

        println(answer)
    }

    fun setQueen(n: Int, curRow: Int) {
        if (curRow == n) {
            answer++
            return
        }

        for (column in 0 until n) {
            val leftTopValue = curRow - column + n - 1
            val rightTopValue = curRow + column
            if (columnUsed[column] || leftTopUsed[leftTopValue] || rightTopUsed[rightTopValue]) continue

            columnUsed[column] = true; leftTopUsed[leftTopValue] = true; rightTopUsed[rightTopValue] = true
            setQueen(n, curRow + 1)
            columnUsed[column] = false; leftTopUsed[leftTopValue] = false; rightTopUsed[rightTopValue] = false
        }
    }
}