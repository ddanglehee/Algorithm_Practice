class EscapeMazeCommand {

    val dr = intArrayOf(1, 0, 0, -1)
    val dc = intArrayOf(0, -1, 1, 0)
    val d = arrayOf('d', 'l', 'r', 'u')

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = "impossible"

        val queue = ArrayDeque<Pair<Point, String>>()
        queue.add(Pair(Point(x, y), ""))

        while (queue.isNotEmpty()) {
            val (point, route) = queue.removeFirst()

            if (route.length == k) {
                if (point.i == r && point.j == c) {
                    answer = route
                    break
                }
                continue
            }

            for (i in 0 until 4) {
                val nR = point.i + dr[i]
                val nC = point.j + dc[i]

                if (nR in 1..n && nC in 1..n) {
                    queue.add(Pair(Point(nR, nC), route + d[i]))
                }
            }
        }

        return answer
    }
}

data class Point(val i: Int, val j: Int)