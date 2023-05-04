class Solution {
    fun solution(gems: Array<String>): IntArray {
        val answer = IntArray(2)
        val gemCountMap = mutableMapOf<String, Int>()

        gems.forEach { gem ->
            gemCountMap[gem] = (gemCountMap[gem] ?: 0) + 1
        }

        var start = 0; var end = gems.lastIndex
        while (start <= end) {
            val gem = gems[end]
            if (gemCountMap[gem] == 1) break
            gemCountMap[gem] = gemCountMap[gem]!! - 1
            end--
        }
        answer[0] = start + 1; answer[1] = end + 1

        while (start <= end && end <= gems.lastIndex) {
            val gem = gems[start]
            if (gemCountMap[gem] == 1) {
                while (gems[end] != gem) {
                    end++
                    if (gems.size == end) break
                    gemCountMap[gems[end]] = gemCountMap[gems[end]]!! + 1
                }
            }
            if (gems.size == end) break
            gemCountMap[gem] = gemCountMap[gem]!! - 1
            start++

            if (Math.abs(end - start) < answer[1] - answer[0]) {
                answer[0] = start + 1
                answer[1] = end + 1
            }
        }

        return answer
    }
}