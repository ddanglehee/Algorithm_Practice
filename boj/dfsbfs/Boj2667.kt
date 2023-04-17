val dr = intArrayOf(0, 0, -1, 1)
val dc = intArrayOf(-1, 1, 0, 0)
var danji = 0
var count = 0
val countList = mutableListOf<Int>()

fun main() = with(System.`in`.bufferedReader()) {

    val sb = StringBuilder()

    val n = readLine().toInt()
    val graph = Array(n) {
        CharArray(n)
    }
    val visited = Array(n) {
        BooleanArray(n)
    }

    for (i in 0 until n) {
        graph[i] = readLine().toCharArray()
    }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (graph[i][j] == '1' && !visited[i][j]) {
                danji++
                count = 0
                dfs(n, graph, visited, i, j)
                countList.add(count)
            }
        }
    }

    sb.append(danji).append("\n")
    countList.sort()
    for (c in countList) {
        sb.append(c).append("\n")
    }

    print(sb)
}

fun dfs(n: Int, graph: Array<CharArray>, visited: Array<BooleanArray>, i: Int, j: Int) {
    visited[i][j] = true
    count++

    for (d in 0 until 4) {
        val nR = i + dr[d]
        val nC = j + dc[d]

        if (nR in 0 until n && nC in 0 until n) {
            if (graph[nR][nC] == '1' && !visited[nR][nC]) {
                dfs(n, graph, visited, nR, nC)
            }
        }
    }
}