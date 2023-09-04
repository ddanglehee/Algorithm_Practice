import kotlin.math.abs

// 정답 코드
// 상하좌우 우선순위 조건 있는 건 DFS로 풀자! 그리고 가지치기 생각하자!
class Solution {

    val dr = intArrayOf(1, 0, 0, -1)
    val dc = intArrayOf(0, -1, 1, 0)
    val direction = arrayOf('d', 'l', 'r', 'u')
    var n = 0; var m = 0; var x = 0; var y = 0; var r = 0; var c = 0; var k = 0
    var answer = "impossible"

    fun solution(_n: Int, _m: Int, _x: Int, _y: Int, _r: Int, _c: Int, _k: Int): String {
        n = _n; m = _m; x = _x; y = _y; r = _r; c = _c; k = _k
        val shortestDistance = getDistanceFromDest(x, y)
        if (k - shortestDistance < 0 || (k - shortestDistance) % 2 != 0) return "impossible"
        dfs(x, y, "")
        return answer
    }

    private fun dfs(curR: Int, curC: Int, route: String): Boolean {
        if (route.length == k) {
            if (curR == r && curC == c) {
                answer = route
                return true
            }
            return false
        }

        for (d in 0 until 4) {
            val nR = curR + dr[d]
            val nC = curC + dc[d]

            if (nR in 1..n && nC in 1..m
                && getDistanceFromDest(nR, nC) + route.length + 1 <= k)
            {
                if (dfs(nR, nC, route + direction[d])) return true
            }
        }

        return false
    }

    private fun getDistanceFromDest(i: Int, j: Int): Int {
        return abs(i - r) + abs(j - c)
    }
}

// 틀린 코드
class EscapeMazeCommand {

    val dr = intArrayOf(1, 0, 0, -1)
    val dc = intArrayOf(0, -1, 1, 0)
    val d = arrayOf('d', 'l', 'r', 'u')

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        var answer: String = "impossible"

        val queue = ArrayDeque<Pair<Point, String>>()
        queue.add(Pair(Point(x, y), ""))

        while (queue.isNotEmpty()) {
            val (point, route) = queue.removeFirst()

            if (route.length == k) {
                if (point.i == r && point.j == c) {
                    answer = route
                    break
                }
                continue
            }

            for (i in 0 until 4) {
                val nR = point.i + dr[i]
                val nC = point.j + dc[i]

                if (nR in 1..n && nC in 1..n) {
                    queue.add(Pair(Point(nR, nC), route + d[i]))
                }
            }
        }

        return answer
    }
}

data class Point(val i: Int, val j: Int)