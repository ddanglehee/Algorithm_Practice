import kotlin.math.min

class Make1 {
    fun solution() {
        val n = System.`in`.bufferedReader().readLine().toInt()
        val dpTable = MutableList(n + 1) { 0 }

        for (i in 2..n) {
            dpTable[i] = dpTable[i - 1] + 1
            if (i % 2 == 0) {
                dpTable[i] = min(dpTable[i], dpTable[i / 2] + 1)
            }
            if (i % 3 == 0) {
                dpTable[i] = min(dpTable[i], dpTable[i / 3] + 1)
            }
            if (i % 5 == 0) {
                dpTable[i] = min(dpTable[i], dpTable[i / 5] + 1)
            }
        }

        println(dpTable[n])
    }
}