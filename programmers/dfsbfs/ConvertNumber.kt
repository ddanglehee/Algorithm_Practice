class ConvertNumber {
    // BFS를 이용한 풀이
    fun solution(x: Int, y: Int, n: Int): Int = bfs(x, y, n)

    private fun bfs(x: Int, y: Int, n: Int): Int {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(y, 0))

        while (queue.isNotEmpty()) {
            val (number, depth) = queue.removeFirst()

            if (number == x) return depth

            if (x <= number - n) {
                queue.add(Pair(number - n, depth + 1))
            }
            if (x <= number / 2 && number % 2 == 0) {
                queue.add(Pair(number / 2, depth + 1))
            }
            if (x <= number / 3 && number % 3 == 0) {
                queue.add(Pair(number / 3, depth + 1))
            }
        }

        return -1
    }

    // DP를 이용한 풀이
    private solution2(x: Int, y: Int, n: Int): Int {
        val dpTable = IntArray(y + 1) { 1000001 }

        dpTable[x] = 0
        for (i in (x + 1)..y) {
            val a = if (x <= i - n) dpTable[i - n] + 1 else 1000001
            val b = if (i % 2 == 0) dpTable[i / 2] + 1 else 1000001
            val c = if (i % 3 == 0) dpTable[i / 3] + 1 else 1000001

            dpTable[i] = minOf(a, minOf(b, c))
        }

        return if (1000000 < dpTable[y]) -1 else dpTable[y]
    }
}