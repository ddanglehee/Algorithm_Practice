class LawOfLargeNumber {
    fun solution() = with (System.`in`.bufferedReader()) {
        val (n, m, k) = readLine().split(" ").map { it.toInt() }
        val input = readLine().split(" ").map { it.toInt() }.toMutableList()

        input.sortDescending()
        val first = input[0]
        val second = input[1]

        val firstAddedCount = (m / (k + 1) * k) + (m % (k + 1))

        val result = first * firstAddedCount + second * (m - firstAddedCount)
        println(result)
    }
}