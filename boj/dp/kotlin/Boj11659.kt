class Boj11659 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val numbers = readLine().split(" ").map { it.toInt() }
        val prefixSum = Array(n) { numbers[it] }

        for (i in 1 until n) {
            prefixSum[i] += prefixSum[i - 1]
        }

        val sb = StringBuilder()
        repeat(m) {
            val (i, j) = readLine().split(" ").map { it.toInt() }
            sb.append(prefixSum[j - 1] - prefixSum[i - 1] + numbers[i - 1]).append("\n")
        }

        println(sb)
    }
}