class SmallSubstring {
    fun solution(t: String, p: String): Int {
        var answer: Int = 0

        val pInt = p.toLong()
        for (i in 0..(t.length - p.length)) {
            val subStr = t.substring(i, i + p.length).toLong()
            if (subStr <= pInt) {
                answer++
            }
        }

        return answer
    }
}