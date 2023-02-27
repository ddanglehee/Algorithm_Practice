class UninhabitedIslandTravel {

    fun solution(maps: Array<String>): IntArray {
        val answer = mutableListOf<Int>()
        val visited = Array(maps.size) { BooleanArray(maps[0].length) }

        maps.forEachIndexed { i, row ->
            row.forEachIndexed { j, info ->
                if (info != 'X' && !visited[i][j]) {
                    answer.add(calculateSurvivalDays(maps, visited, Pair(i, j)))
                }
            }
        }

        if (answer.isEmpty()) answer.add(-1)
        answer.sort()
        return answer.toIntArray()
    }

    private fun calculateSurvivalDays(maps: Array<String>, visited: Array<BooleanArray>, start: Pair<Int, Int>): Int {
        val dx = intArrayOf(0, 0, -1, 1)
        val dy = intArrayOf(-1, 1, 0, 0)

        var survivalDays = maps[start.first][start.second] - '0'
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.add(Pair(start.first, start.second))
        visited[start.first][start.second] = true

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()

            for (i in 0 until 4) {
                val tmpX = x + dx[i]; val tmpY = y + dy[i]
                if (tmpX in 0 until maps.size && tmpY in 0 until maps[0].length && !visited[tmpX][tmpY] && maps[tmpX][tmpY] != 'X') {
                    survivalDays += maps[tmpX][tmpY] - '0'
                    queue.add(Pair(tmpX, tmpY))
                    visited[tmpX][tmpY] = true
                }
            }
        }

        return survivalDays
    }
}