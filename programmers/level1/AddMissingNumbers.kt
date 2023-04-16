class Solution {
    fun solution(numbers: IntArray): Int {
        var answer: Int = 45

        numbers.forEach { number ->
            answer -= number
        }

        return answer
    }
}