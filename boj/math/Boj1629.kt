class Boj1629 {

    // 1. 재귀를 이용한 풀이
    fun main() = with(System.`in`.bufferedReader()) {
        val (a, b, c) = readLine().split(" ").map { it.toLong() }

        print(solution(a, b, c))
    }

    fun solution(a: Long, b: Long, c: Long): Long {
        if (b == 1L) return a % c

        val tmp = solution(a, b / 2, c) % c

        return if (b % 2 == 0L) tmp * tmp % c else ((tmp * tmp % c) * (a % c)) % c
    }

    // 2. 반복문을 이용한 풀이
    fun main() = with(System.`in`.bufferedReader()) {
        var (a, b, c) = readLine().split(" ").map { it.toLong() }

        var answer = 1L

        while (b != 0L) {
            if (b % 2L == 1L) {
                answer *= a
                answer %= c
            }

            a *= a
            a %= c
            b /= 2L
        }

        print(answer)
    }
}