class Boj1629 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (a, b, c) = readLine().split(" ").map { it.toLong() }

        print(solution(a, b, c))
    }

    fun solution(a: Long, b: Long, c: Long): Long {
        if (b == 1L) return a % c

        val tmp = solution(a, b / 2, c) % c

        return if (b % 2 == 0L) tmp * tmp % c else ((tmp * tmp % c) * (a % c)) % c
    }
}