class Network {

    fun solution(n: Int, computers: Array<IntArray>): Int {
        var answer = 0
        val visited = BooleanArray(n)

        for (i in 0 until n) {
            if (visited[i]) continue

            dfs(i, computers, visited)
            answer++
        }

        return answer
    }

    private fun dfs(current: Int, computers: Array<IntArray>, visited: BooleanArray) {
        visited[current] = true

        computers[current].forEachIndexed { next, isConnected ->
            if (!visited[next] && isConnected == 1) {
                dfs(next, computers, visited)
            }
        }
    }
}