class ArcheryContest {

    var answer = IntArray(11)
    var diff = 0
    var apeachScore = 0
    var ryanScore = 0

    fun solution(n: Int, info: IntArray): IntArray {

        info.forEachIndexed { index, count ->
            if (count != 0) {
                apeachScore += (10 - index)
            }
        }
        dfs(n, 10, true, IntArray(11), info)
        dfs(n, 10, false, IntArray(11), info)

        if (diff == 0) answer = intArrayOf(-1)
        return answer
    }

    private fun dfs(count: Int, score: Int, win: Boolean, ryanInfo: IntArray, apeachInfo: IntArray) {
        if (count == 0) {
            if (apeachScore < ryanScore && diff <= ryanScore - apeachScore) {
                if (diff == ryanScore - apeachScore) {
                    for (i in 10 downTo 0) {
                        if (ryanInfo[i] < answer[i]) {
                            return
                        } else if (answer[i] < ryanInfo[i]) {
                            break
                        }
                    }
                }

                for (i in 0..10) {
                    answer[i] = ryanInfo[i]
                }
                diff = ryanScore - apeachScore
            }
            return
        }
        if (score < 0 || (win && count <= apeachInfo[10 - score])) return

        if (win) {
            val newCount = count - (apeachInfo[10 - score] + 1)
            ryanInfo[10 - score] = apeachInfo[10 - score] + 1
            ryanScore += score
            if (apeachInfo[10 - score] != 0) {
                apeachScore -= score
            }
            dfs(newCount, score - 1, true, ryanInfo, apeachInfo)
            dfs(newCount, score - 1, false, ryanInfo, apeachInfo)
            ryanScore -= score
            ryanInfo[10 - score] = 0
            if (apeachInfo[10 - score] != 0) {
                apeachScore += score
            }
        } else {
            if (score == 0 && 0 < count) {
                ryanInfo[10] = count
                dfs(0, score - 1, true, ryanInfo, apeachInfo)
                ryanInfo[10] = 0
            }
            dfs(count, score - 1, true, ryanInfo, apeachInfo)
            dfs(count, score - 1, false, ryanInfo, apeachInfo)
        }
    }
}