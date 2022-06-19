class FloorWork {
    fun solution() {
        val n = System.`in`.bufferedReader().readLine().toInt()
        val dpTable = Array(n + 1) { 0 }

        dpTable[1] = 1
        dpTable[2] = 3

        for (i in 3..n) {
            dpTable[i] = (dpTable[i - 1] + 2 * dpTable[i - 2]) % 796796
        }

        println(dpTable[n])
    }
}