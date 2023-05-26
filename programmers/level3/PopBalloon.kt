class Solution {
    fun solution(a: IntArray): Int {
        var answer: Int = 0
        val leftSmallerExist = BooleanArray(a.size)
        val rightSmallerExist = BooleanArray(a.size)

        var smallest = a[0]
        // 자기 기준 왼쪽에 자신보다 작은 숫자가 있는지 표시
        for (i in 1 until a.size) {
            if (a[i] < smallest) {
                smallest = a[i]
            } else {
                leftSmallerExist[i] = true
            }
        }

        smallest = a.last()
        // 자기 기준 오른쪽에 자신보다 작은 숫자가 있는지 표시
        for (i in (a.lastIndex - 1) downTo 0) {
            if (a[i] < smallest) {
                smallest = a[i]
            } else {
                rightSmallerExist[i] = true
            }
        }

        for (i in a.indices) {
            if (!leftSmallerExist[i] || !rightSmallerExist[i]) answer++
        }

        return answer
    }
}