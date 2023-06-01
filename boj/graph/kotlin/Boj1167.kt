class Boj1167 {

    var maxDist = 0
    var farNode = 0

    fun main() = with(System.`in`.bufferedReader()) {
        val v = readLine().toInt()
        val tree = Array(v + 1) {
            mutableListOf<Pair<Int, Int>>()
        }

        for (i in 0 until v) {
            val input = readLine().split(" ").map { it.toInt() }

            val n = input[0]

            for (j in 0 until (input.lastIndex - 1) / 2) {
                val m = input[2 * j + 1]
                val d = input[2 * j + 2]
                tree[n].add(m to d)
            }
        }

        dfs(1, tree, BooleanArray(v + 1), 0)
        val start = farNode
        maxDist = 0
        farNode = 0
        dfs(start, tree, BooleanArray(v + 1), 0)

        print(maxDist)
    }

    fun dfs(curN: Int, tree: Array<MutableList<Pair<Int, Int>>>, visited: BooleanArray, dist: Int) {
        visited[curN] = true

        for (next in tree[curN]) {
            if (!visited[next.first]) {
                val newDist = dist + next.second
                if (maxDist < newDist) {
                    maxDist = newDist
                    farNode = next.first
                }
                dfs(next.first, tree, visited, newDist)
            }
        }
    }
}