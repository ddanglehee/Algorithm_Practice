class Boj2559 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val input = readLine().split(" ").map { it.toInt() }.toIntArray()

        var answer = 0
        for (i in 0 until k) {
            answer += input[i]
        }

        for (i in 1 until n) {
            input[i] += input[i - 1]
        }

        for (i in 1..(n - k)) {
            if (answer < input[i + k - 1] - input[i - 1]) {
                answer = input[i + k - 1] - input[i - 1]
            }
        }

        println(answer)
    }
}