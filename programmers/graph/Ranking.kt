class Ranking {

    private var playerCount: Int = 0
    private lateinit var winGraph: Array<MutableList<Int>>
    private lateinit var loseGraph: Array<MutableList<Int>>

    fun solution(n: Int, results: Array<IntArray>): Int {
        var answer = 0
        playerCount = n

        init(n)

        results.forEach { result ->
            val winner = result[0]
            val loser = result[1]

            winGraph[winner].add(loser)
            loseGraph[loser].add(winner)
        }

        for (player in 1..n) {
            val result = bfs(player, winGraph) + bfs(player, loseGraph)
            if (result == n - 1) {
                answer++
            }
        }

        return answer
    }

    private fun init(n: Int) {
        winGraph = Array(n + 1) { mutableListOf<Int>() }
        loseGraph = Array(n + 1) { mutableListOf<Int>() }
    }

    private fun bfs(start: Int, graph: Array<MutableList<Int>>): Int {
        var count = 0
        val visited = BooleanArray(playerCount + 1)
        val queue = ArrayDeque<Int>()

        queue.add(start)
        visited[start] = true
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()

            graph[current].forEach { next ->
                if (!visited[next]) {
                    queue.add(next)
                    visited[next] = true
                    count++
                }
            }
        }

        return count
    }
}