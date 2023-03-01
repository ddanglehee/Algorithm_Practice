class ConvertNumber {
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
}