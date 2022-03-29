class MakeTteokbokkiRiceCake {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        val riceCake = br.readLine().split(" ").map { it.toInt() }

        var start = 0
        var end = riceCake.maxOrNull()!!
        var result = 0


        while (start <= end) {
            var sum = 0
            val h = (start + end) / 2

            riceCake.forEach { length ->
                if (0 < length - h) {
                    sum += length - h
                }
            }

            when {
                m <= sum -> {
                    result = h
                    start = h + 1
                }
                else -> end = h - 1
            }
        }
        println(result)
    }
}