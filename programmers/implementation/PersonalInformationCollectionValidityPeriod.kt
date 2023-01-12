class PersonalInformationCollectionValidityPeriod {

    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val answer = mutableListOf<Int>()

        // 초기화
        val (todayY, todayM, todayD) = today.split(".").map { it.toInt() }
        val termsMap = mutableMapOf<String, Int>()
        terms.forEach { it ->
            val (kinds, term) = it.split(" ")
            termsMap[kinds] = term.toInt()
        }

        // 개인정보 파기 여부 결정
        privacies.forEachIndexed { idx, privacy ->
            val (date, kind) = privacy.split(" ")
            val (expiredY, expiredM, expiredD) = calculateExpiredDate(date, kind, termsMap)

            if (expiredY < todayY || (expiredY == todayY && expiredM < todayM) || (expiredY == todayY && expiredM == todayM && expiredD <= todayD)) {
                answer.add(idx + 1)
            }
        }

        return answer.toIntArray()
    }

    private fun calculateExpiredDate(date: String, kind: String, termsMap: Map<String, Int>): IntArray {
        val (y, m, d) = date.split(".").map { it.toInt() }
        var expiredY = y; var expiredM = m; var expiredD = d

        expiredM += termsMap[kind]!!
        while (12 < expiredM) {
            expiredM -= 12
            expiredY += 1
        }

        return intArrayOf(expiredY, expiredM, expiredD)
    }
}