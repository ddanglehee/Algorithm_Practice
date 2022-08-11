class Fatigue {

    private val visited = BooleanArray(8)
    private var count = 0
    private var answer = 0

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        for (i in dungeons.indices) {
            dfs(k, i, dungeons)
        }
        return answer
    }

    private fun dfs(k: Int, current:Int, dungeons: Array<IntArray>) {
        visited[current] = true
        count++

        if (answer < count) {
            answer = count
        }

        for (i in dungeons.indices) {
            val newK = k - dungeons[current][1]
            if (!visited[i] && (dungeons[i][0] <= newK)) {
                dfs(newK, i, dungeons)
            }
        }

        visited[current] = false
        count--
    }
}