class InterceptSystem {
    fun solution(targets: Array<IntArray>): Int {
        var answer: Int = 0
        targets.sortWith(compareBy<IntArray> { it[0] }.thenBy { it[1] })
        var curS = targets[0][0]
        var curE = targets[0][1]

        for (i in 1..targets.lastIndex) {
            val target = targets[i]

            // 함께 요격이 가능하다면
            if (target[0] in curS until curE) {
                curS = (maxOf(target[0], curS))
                curE = (minOf(target[1], curE))
            } else {
                answer++
                curS = target[0]
                curE = target[1]
            }
        }

        return answer + 1
    }
}