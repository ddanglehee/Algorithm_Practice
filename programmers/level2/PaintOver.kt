class PaintOver {
    fun solution(n: Int, m: Int, sections: IntArray): Int {
        var answer: Int = 0
        var lastColoredSection = 0

        for (section in sections) {
            if (n < lastColoredSection) break;
            if (section <= lastColoredSection) continue

            answer++
            lastColoredSection = section + m - 1
        }

        return answer
    }
}