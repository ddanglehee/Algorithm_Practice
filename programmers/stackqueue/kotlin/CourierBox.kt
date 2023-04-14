class Solution {
    fun solution(order: IntArray): Int {
        var answer: Int = 0
        val n = order.size
        val belt = IntArray(n) { i -> i + 1 }
        val subBelt = mutableListOf<Int>()

        var index = 0 // n보다 작아야함
        for (box in order) {
            var can = false
            if (subBelt.isNotEmpty() && subBelt.last() == box) {
                answer++
                can = true
                subBelt.removeLast()
            } else {
                while (index < n && belt[index] != box) {
                    subBelt.add(belt[index++])
                }

                if (index < n && belt[index] == box) {
                    answer++
                    can = true
                    index++
                }
            }

            if (!can) {
                break
            }
        }

        return answer
    }
}