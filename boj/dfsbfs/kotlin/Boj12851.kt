class Boj12851 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }

        if (k <= n) {
            println(n - k)
            println(1)
            return
        }

        val visited = IntArray(2 * k + 1) { k }
        val queue = ArrayDeque<Pair<Int, Int>>()
        var shortcut = k
        var count = 0

        queue.add(n to 0)
        while (queue.isNotEmpty()) {
            val (cur, dist) = queue.removeFirst()

            if (cur == k) {
                shortcut = dist
                count++
                break
            }

            val newDist = dist + 1
            if (0 <= cur - 1 && newDist <= visited[cur - 1]) {
                visited[cur - 1] = newDist
                queue.add(cur - 1 to newDist)
            }
            if (newDist <= visited[cur + 1]) {
                visited[cur + 1] = newDist
                queue.add(cur + 1 to newDist)
            }
            if (cur * 2 <= visited.size && newDist <= visited[cur * 2]) {
                visited[cur * 2] = newDist
                queue.add(cur * 2 to newDist)
            }
        }

        while (queue.isNotEmpty()) {
            val (cur, dist) = queue.removeFirst()
            if (shortcut < dist) break
            if (cur == k && shortcut == dist) count++
        }

        println(shortcut)
        println(count)
    }
}