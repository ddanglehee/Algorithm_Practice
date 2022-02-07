class NumberCardGame {
    fun solution() = with (System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val card = mutableListOf<List<Int>>()
        for (i in 0 until n) {
            val input = readLine().split(" ").map { it.toInt() }
            card.add(input)
        }

        var result = 0
        for (i in 0 until n) {
            val min = card[i].minOrNull()!! // O(M)
            result = maxOf(result, min)
        } // O(NM)

        println(result)
    }
}