import kotlin.math.min

class EfficientMoneyComposition {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val moneyList = mutableListOf<Int>()
        repeat(n) {
            moneyList.add(br.readLine().toInt())
        }

        val dpTable = Array(m + 1) { 10001 }
        moneyList.forEach { unit ->
            if (unit <= m) {
                dpTable[unit] = 1
            }
        }

        val start = moneyList.minOrNull()!!
        for (i in start + 1..m) {
            moneyList.forEach { unit ->
                if (0 < i - unit && dpTable[i - unit] != 10001) {
                    dpTable[i] = min(dpTable[i], dpTable[i - unit] + 1)
                }
            }
        }

        if (dpTable[m] == 10001) {
            println(-1)
        } else {
            println(dpTable[m])
        }
    }
}