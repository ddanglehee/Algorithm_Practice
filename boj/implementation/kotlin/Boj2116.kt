class Boj2116 {

    val xSetList = listOf(setOf(0, 5), setOf(1, 3), setOf(2, 4))

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val dices = Array(n) {
            readLine().split(" ").map { it.toInt() }
        }

        var answer = 0
        var tmp: Int

        for (topIndex in 0 until 6) {
            tmp = when(topIndex) {
                0, 5 -> {
                    getMax(dices[0], xSetList[0]) + solution(1, topIndex, dices)
                }
                1, 3 -> {
                    getMax(dices[0], xSetList[1]) + solution(1, topIndex, dices)
                }
                else -> {
                    getMax(dices[0], xSetList[2]) + solution(1, topIndex, dices)
                }
            }
            answer = maxOf(answer, tmp)
        }

        print(answer)
    }

    fun solution(turn: Int, topIndex: Int, dices: Array<List<Int>>): Int {
        if (dices.size == turn) return 0

        for (i in 0 until 6) {
            if (dices[turn - 1][topIndex] != dices[turn][i]) continue

            return when(i) {
                0, 5 -> {
                    val nextTopIndex = if (i == 0) 5 else 0
                    getMax(dices[turn], xSetList[0]) + solution(turn + 1, nextTopIndex, dices)
                }
                1, 3 -> {
                    val nextTopIndex = if (i == 1) 3 else 1
                    getMax(dices[turn], xSetList[1]) + solution(turn + 1, nextTopIndex, dices)
                }
                else -> {
                    val nextTopIndex = if (i == 2) 4 else 2
                    getMax(dices[turn], xSetList[2]) + solution(turn + 1, nextTopIndex, dices)
                }
            }
        }

        return 0
    }

    fun getMax(dice: List<Int>, xSet: Set<Int>): Int {
        var max = 0

        for (i in 0 until 6) {
            if (i in xSet) continue
            max = maxOf(max, dice[i])
        }

        return max
    }
}