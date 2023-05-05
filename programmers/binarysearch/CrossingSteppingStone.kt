class Solution {
    fun solution(stones: IntArray, k: Int): Int {
        var answer = 0
        var max = stones.maxOrNull()!!
        var min = stones.minOrNull()!!

        while (min <= max) {
            val mid = (max + min) / 2

            var noCount = 0
            var canGo = true
            for (num in stones) {
                if (num - mid < 0) {
                    noCount++
                } else {
                    if (k <= noCount) {
                        canGo = false
                        break
                    }
                    noCount = 0
                }
            }
            if (k <= noCount) canGo = false

            if (canGo) {
                answer = mid
                min = mid + 1
            } else {
                max = mid - 1
            }
        }

        return answer
    }
}