class LawOfLargeNumber {
    fun solution() {
        val (n, m, k) = readLine()!!.split(" ").map { it.toInt() }
        val input = readLine()!!.split(" ").map { it.toInt() }.toMutableList()

        input.sortDescending()
        val first = input[0]
        val second = input[1]

        val result = (first * k + second) * (m / (k + 1)) + first * (m % (k + 1))
        println(result)
    }
}