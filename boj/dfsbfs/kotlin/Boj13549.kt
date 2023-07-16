class Boj13549 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val visited = IntArray(200001) { 100000 }

        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(n to 0)
        visited[n] = 0
        while (queue.isNotEmpty()) {
            val (point, time) = queue.removeFirst()

            if (point == k) break

            if (visited[point] < time) continue

            if (point * 2 <= 2 * k && time < visited[point * 2]) {
                visited[point * 2] = time
                queue.add(point * 2 to time)
            }
            if (point + 1 <= 2 * k && time + 1 < visited[point + 1]) {
                visited[point + 1] = time + 1
                queue.add(point + 1 to time + 1)
            }
            if (0 <= point - 1 && time + 1 < visited[point - 1]) {
                visited[point - 1] = time + 1
                queue.add(point - 1 to time + 1)
            }
        }

        print(visited[k])
    }
}