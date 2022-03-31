import kotlin.math.min

class EfficientMoneyComposition {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val moneyList = mutableListOf<Int>()
        repeat(n) {
            moneyList.add(br.readLine().toInt())
        }

        val dpTable = Array(10001) { 10001 }

        moneyList.forEach { unit ->
            dpTable[unit] = 1
            for (i in unit + 1..m) {
                if (dpTable[i - unit] != 10001) {
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