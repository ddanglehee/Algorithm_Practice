class Boj2847 {
    fun solution() = with (System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val scores = mutableListOf<Int>()

        for (i in 0 until n) {
            val score = readLine().toInt()
            scores.add(score)
        }

        var result = 0
        scores.reverse()
        for (i in 0 until scores.lastIndex) {
            if (scores[i] > scores[i + 1]) continue

            result += scores[i + 1] - scores[i] + 1
            scores[i + 1] = scores[i] - 1
        }

        println(result)
    }
}