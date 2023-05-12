class Boj1351 {

    val map = mutableMapOf<Long, Long>()

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, p, q) = readLine().split(" ").map { it.toLong() }

        print(solve(n, p, q))
    }

    fun solve(n: Long, p: Long, q: Long): Long {
        if (n == 0L) return 1

        if (map[n / p] == null) {
            map[n / p] = solve(n / p, p, q)
        }
        if (map[n / q] == null) {
            map[n / q] = solve(n / q, p, q)
        }

        return map[n / p]!! + map[n / q]!!
    }
}