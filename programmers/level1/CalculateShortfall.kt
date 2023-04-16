class Solution {
    fun solution(price: Int, money: Int, count: Int): Long {
        val sum = LongArray(2501)

        for (i in 1..count) {
            sum [i] = sum[i - 1] + i
        }

        val answer: Long = if (price * sum[count] <= money) 0 else (price * sum[count]) - money
        return answer
    }
}