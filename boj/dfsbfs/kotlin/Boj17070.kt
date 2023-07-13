class Boj17070 {

    var answer = 0
    val checkCanGo = arrayOf(
        arrayOf(Pair(0, arrayOf(Pair(0, 2))), Pair(2, arrayOf(Pair(1, 1), Pair(1, 2), Pair(0, 2)))),
        arrayOf(Pair(1, arrayOf(Pair(2, 0))), Pair(2, arrayOf(Pair(2, 0), Pair(2, 1), Pair(1, 1)))),
        arrayOf(Pair(0, arrayOf(Pair(1, 2))), Pair(1, arrayOf(Pair(2, 1))), Pair(2, arrayOf(Pair(1, 2), Pair(2, 1), Pair(2, 2))))
    )
    val next = arrayOf(Pair(0, 1), Pair(1, 0), Pair(1, 1))

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val graph = Array(n) {
            readLine().split(" ").map { it.toInt() }.toIntArray()
        }

        dfs(graph, n-1, 0, 0, 0)

        print(answer)
    }

    fun dfs(graph: Array<IntArray>, n: Int, curR: Int, curC: Int, curD: Int) {
        graph[curR][curC] = 1

        if ((curR == n && curC == n-1 && curD == 0)
            || (curR == n-1 && curC == n && curD == 1)
            || (curR == n-1 && curC == n-1 && curD == 2)) {
            answer++
        } else {
            val nR = curR + next[curD].first
            val nC = curC + next[curD].second

            if (nR in 0..n && nC in 0..n && graph[nR][nC] == 0) {
                checkCanGo[curD].forEach { (nD, checkArray) ->
                    var flag = false
                    for ((dr, dc) in checkArray) {
                        val cR = curR + dr
                        val cC = curC + dc
                        if (!(cR in 0..n && cC in 0..n && graph[cR][cC] == 0)) {
                            flag = true
                            break
                        }
                    }
                    if (!flag) dfs(graph, n, nR, nC, nD)
                }
            }
        }

        graph[curR][curC] = 0
    }
}