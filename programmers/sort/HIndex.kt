class HIndex {
    fun solution(citations: IntArray): Int {
        var answer = 0
        citations.sort()

        for (h in citations.maxOrNull()!! downTo 0) {
            val index = lowerBound(citations, h)

            if (h <= citations.lastIndex - index + 1) {
                answer = h
                break
            }
        }

        return answer
    }

    private fun lowerBound(array: IntArray, key: Int): Int {
        var i = 0
        var j = array.lastIndex

        while (i < j) {
            val mid = (i + j) / 2

            if (key <= array[mid]) {
                j = mid
            } else {
                i = mid + 1
            }
        }

        return j
    }
}