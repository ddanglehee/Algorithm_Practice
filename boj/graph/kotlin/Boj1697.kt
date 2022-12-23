class Boj1697 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }

        if (k <= n) {
            println(n - k)
        } else {
            println(bfs(n, k))
        }
    }

    fun bfs(n: Int, k: Int): Int {
        val visited = BooleanArray(2 * k + 1)
        visited[n] = true

        val queue = ArrayDeque<Pair<Int, Int>>() // Pair<위치, 초>
        queue.add(n to 0)
        while (queue.isNotEmpty()) {
            val (location, time) = queue.removeFirst()

            if (location == k) {
                return time
            }

            val nextLocationList = listOf(location - 1, location + 1, 2 * location)
            for (nextLocation in nextLocationList) {
                if (0 < nextLocation && nextLocation <= 2 * k && !visited[nextLocation]) {
                    visited[nextLocation] = true
                    queue.add(nextLocation to time + 1)
                }
            }
        }

        return -1
    }
}