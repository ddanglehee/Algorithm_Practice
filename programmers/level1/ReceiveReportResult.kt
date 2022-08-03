class Solution {
    fun solution(id_list: Array<String>, reports: Array<String>, k: Int): IntArray {
        val answer = IntArray(id_list.size) { 0 }

        // 1. reportMap 초기화
        val reportMap = hashMapOf<String, Report>()
        id_list.forEachIndexed { index, id ->
            reportMap[id] = Report(index)
        }

        // 2. 신고 내역 반영하기
        reports.forEach { report ->
            val (reporter, respondent) = report.split(" ")

            if (respondent !in reportMap[reporter]!!.reportIdSet) {
                reportMap[reporter]!!.reportIdSet.add(respondent)
                reportMap[respondent]!!.reportedCount++
            }
        }

        // 3. 신고 결과 처리하기
        id_list.forEach { id ->
            reportMap[id]!!.reportIdSet.forEach { reportedId ->
                if (k <= reportMap[reportedId]!!.reportedCount) {
                    answer[reportMap[id]!!.index]++
                }
            }
        }
        return answer
    }

    inner class Report(val index: Int) {
        val reportIdSet = mutableSetOf<String>()
        var reportedCount = 0
    }
}