package dfsbfs

class FreezingBeverage {
    fun solution() {
        val br = System.`in`.bufferedReader()

        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val mold = MutableList(n) {
            br.readLine().split("").subList(1, m + 1).map { it.toInt() }.toMutableList()
        }

        var iceCream = 0

        for (row in 0 until n) {
            for (column in 0 until m) {
                if (dfs(mold, row, column)) {
                    iceCream++
                }
            }
        }

        println(iceCream)
    }

    fun dfs(mold: MutableList<MutableList<Int>>, row: Int, column: Int): Boolean {
        if (row !in mold.indices || column !in mold[0].indices) return false

        if (mold[row][column] == 0) {
            mold[row][column] = 1
            val directions = listOf(Pair(0, -1), Pair(0, 1), Pair(-1, 0), Pair(1, 0))

            for (d in directions) {
                dfs(mold, row + d.first, column + d.second)
            }
            return true
        }
        return false
    }
}