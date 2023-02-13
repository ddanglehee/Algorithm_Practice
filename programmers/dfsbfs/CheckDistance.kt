class CheckDistance {

    private val visited = Array(5) { BooleanArray(5) }
    private val di = intArrayOf(0, 0, -1, 1)
    private val dj = intArrayOf(1, -1, 0, 0)
    private val answer = mutableListOf<Int>()

    fun solution(places: Array<Array<String>>): IntArray {

        places.forEach { place ->
            initVisited()
            answer.add(checkDistance(place))
        }

        return answer.toIntArray()
    }

    private fun initVisited() {
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                visited[i][j] = false
            }
        }
    }

    private fun checkDistance(place: Array<String>): Int{
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (place[i][j] == 'P') {
                    if (!dfs(i, j, 0, place)) return 0
                }
            }
        }
        return 1
    }

    private fun dfs(i: Int, j: Int, depth: Int, graph: Array<String>): Boolean {
        visited[i][j] = true

        if (graph[i][j] == 'P' && depth != 0) {
            return false
        } else if (2 <= depth) {
            return true
        }

        for (k in 0 until 4) {
            val tmpX = i + di[k]; val tmpY = j + dj[k]
            if (tmpX in 0 until 5 && tmpY in 0 until 5) {
                if (!visited[tmpX][tmpY] && graph[tmpX][tmpY] != 'X') {
                    if(!dfs(tmpX, tmpY, depth + 1, graph)) return false
                }
            }
        }

        visited[i][j] = false
        return true
    }
}