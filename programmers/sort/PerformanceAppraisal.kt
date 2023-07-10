class Solution {
    fun solution(scores: Array<IntArray>): Int {
        val wanhoScore = intArrayOf(scores[0][0], scores[0][1])
        scores.sortWith(compareBy<IntArray> { it[0] }.reversed().thenBy { it[1] })

        var max = scores[0][1]
        for (i in 1..scores.lastIndex) {
            if (scores[i][1] < max) {
                if (scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1]) return -1

                scores[i][0] = -1
                scores[i][1] = -1
            } else {
                max = scores[i][1]
            }
        }

        scores.sortWith(compareBy<IntArray> { it[0] + it[1] }.reversed())
        var rank = -1
        for (i in scores.indices) {
            if (scores[i][0] == wanhoScore[0] && scores[i][1] == wanhoScore[1]) {
                rank = i + 1
                break
            }
        }

        return rank
    }
}