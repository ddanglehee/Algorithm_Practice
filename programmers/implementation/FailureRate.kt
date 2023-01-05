class FailureRate {
    fun solution(n: Int, stages: IntArray): IntArray {

        val arriveCount = Array(n + 1) { 0 }
        val notClearCount = Array(n + 1) { 0 }

        stages.forEach { it ->
            val stage = if (it == n + 1) n else it

            for (i in 1..stage) {
                arriveCount[i]++
            }
            if (it != n + 1) { notClearCount[it]++ }
        }

        val failureRateInfo = mutableListOf<Pair<Double, Int>>()
        for (i in 1..n) {
            val failureRate = if (arriveCount[i] == 0) {
                0.0
            } else {
                notClearCount[i].toDouble() / arriveCount[i]
            }

            failureRateInfo.add(failureRate to i)
        }

        failureRateInfo.sortByDescending { it.first }

        return failureRateInfo.map { it.second }.toIntArray()
    }
}