class Boj16918 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (r, c, n) = readLine().split(" ").map { it.toInt() }
        val graph = Array(r) {
            CharArray(c)
        }
        val time = Array(r) {
            IntArray(c)
        }

        for (i in 0 until r) {
            val row = readLine()
            for (j in 0 until c) {
                graph[i][j] = row[j]
                if (row[j] == 'O') {
                    time[i][j] = 0
                }
            }
        }

        if (n == 1) {
            printGraph(graph)
            return
        }

        var t = 2
        val dr = intArrayOf(0, 0, -1, 1)
        val dc = intArrayOf(-1, 1, 0, 0)
        while (t <= n) {
            val tmpList = mutableListOf<Pair<Int, Int>>()
            for (i in 0 until r) {
                for (j in 0 until c) {
                    if (graph[i][j] == 'O' && time[i][j] == t - 3) {
                        graph[i][j] = '.'
                        for (d in 0 until 4) {
                            val nR = i + dr[d]
                            val nC = j + dc[d]

                            if (nR in 0 until r && nC in 0 until c) tmpList.add(nR to nC)
                        }
                    }

                    if (t % 2 == 0 && graph[i][j] == '.') {
                        graph[i][j] = 'O'
                        time[i][j] = t
                    }
                }
            }

            for (location in tmpList) {
                graph[location.first][location.second] = '.'
            }

            t++
        }

        printGraph(graph)
    }

    fun printGraph(graph: Array<CharArray>) {
        val sb = StringBuilder()
        for (row in graph) {
            for (c in row) {
                sb.append(c)
            }
            sb.append("\n")
        }
        print(sb)
    }
}