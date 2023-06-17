class Boj10026 {

    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(-1, 1, 0, 0)

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val graph = Array<String>(n) {
            readLine()
        }
        val visited = Array(n) {
            BooleanArray(n)
        }

        var answer1 = 0
        while(true) {
            var isDone = true

            for(i in 0 until n) {
                for(j in 0 until n) {
                    if (visited[i][j]) continue
                    isDone = false
                    answer1++
                    dfs(intArrayOf(i, j), graph, visited, false, n)
                }
            }

            if (isDone) break
        }

        var answer2 = 0
        for(i in 0 until n) {
            for(j in 0 until n) {
                visited[i][j] = false
            }
        }
        while(true) {
            var isDone = true

            for(i in 0 until n) {
                for(j in 0 until n) {
                    if (visited[i][j]) continue
                    isDone = false
                    answer2++
                    dfs(intArrayOf(i, j), graph, visited, true, n)
                }
            }

            if (isDone) break
        }


        print("$answer1 $answer2")
    }

    fun dfs(cur: IntArray,graph: Array<String>, visited: Array<BooleanArray>, colorWeak: Boolean, n: Int) {
        visited[cur[0]][cur[1]] = true

        for (d in 0 until 4) {
            val nX = cur[0] + dx[d]
            val nY = cur[1] + dy[d]

            if (nX in 0 until n && nY in 0 until n && !visited[nX][nY]) {
                if (colorWeak && graph[cur[0]][cur[1]] != 'B') {
                    if (graph[nX][nY] == 'G' || graph[nX][nY] == 'R') {
                        dfs(intArrayOf(nX, nY), graph, visited, colorWeak, n)
                    }
                } else if(graph[nX][nY] == graph[cur[0]][cur[1]]) {
                    dfs(intArrayOf(nX, nY), graph, visited, colorWeak, n)
                }
            }
        }
    }
}