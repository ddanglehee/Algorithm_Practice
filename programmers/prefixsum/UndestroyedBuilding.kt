class Solution {

    fun solution(board: Array<IntArray>, skills: Array<IntArray>): Int {
        var answer: Int = 0
        val n = board.size
        val m = board[0].size
        val degreeGraph = Array(n) {
            IntArray(m)
        }

        skills.forEach { skill ->
            val type = skill[0]
            val r1 = skill[1]; val c1 = skill[2]
            val r2 = skill[3]; val c2 = skill[4]
            val degree = skill[5]
            val d = if (type == 1) -1 * degree else degree

            degreeGraph[r1][c1] += d
            if (r2 + 1 in 0 until n) degreeGraph[r2 + 1][c1] -= d
            if (c2 + 1 in 0 until m) degreeGraph[r1][c2 + 1] -= d
            if (r2 + 1 in 0 until n && c2 + 1 in 0 until m) degreeGraph[r2 + 1][c2 + 1] += d
        }

        // 누적합 구하기 (행)
        for (r in 0 until n) {
            for (c in 1 until m) {
                degreeGraph[r][c] += degreeGraph[r][c - 1]
            }
        }
        // 누적합 구하기 (열)
        for (c in 0 until m) {
            for (r in 1 until n) {
                degreeGraph[r][c] += degreeGraph[r - 1][c]
            }
        }

        // 변경된 내구도 board에 반영하기
        for (r in 0 until n) {
            for (c in 0 until m) {
                board[r][c] += degreeGraph[r][c]
                if (1 <= board[r][c]) answer++ // 파괴되지 않은 건물 개수 세기
            }
        }

        return answer
    }
}