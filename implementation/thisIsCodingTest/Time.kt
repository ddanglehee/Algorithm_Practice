class Time {
    fun solution() {
        val n = System.`in`.bufferedReader().readLine().toInt()

        var result = 0
        for (h in 0..24) {
            if (n < h) break
            for (m in 0 .. 59) {
                for (s in 0..59) {
                    if ("3" in h.toString() || "3" in m.toString() || "3" in s.toString()) {
                        result++
                    }
                }
            }
        }

        println(result)
    }
}