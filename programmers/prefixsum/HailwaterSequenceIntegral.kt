class Solution {

    val length = mutableListOf<Int>()
    val sum = mutableListOf<Double>(0.0)

    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {

        // 우박수열 구하기
        length.add(k)
        if (k % 2 == 0) {
            ubaksuyeol(k / 2, 1)
        } else {
            ubaksuyeol(k * 3 + 1, 1)
        }

        // 구간합 구하기
        val n = sum.size
        val prefixSum = DoubleArray(n)
        prefixSum[1] = sum[1]
        for (i in 2 until n) {
            prefixSum[i] = prefixSum[i - 1] + sum[i]
        }

        // 정적분 구하기
        val answer = DoubleArray(ranges.size)
        ranges.forEachIndexed { i, range ->
            val start = range[0]
            val end = range[1]
            if (start == 0 && end == 0) {
                answer[i] = prefixSum[n - 1]
            } else if (n + end - 1 < start) {
                answer[i] = 1.0 * (-1)
            } else {
                answer[i] = prefixSum[n + end - 1] - prefixSum[start]
            }
        }


        return answer
    }

    private fun ubaksuyeol(n: Int, i: Int) {
        length.add(n)
        sum.add((length[i - 1] + n).toDouble() / 2)

        if (n == 1) return

        if (n % 2 == 0) {
            ubaksuyeol(n / 2, i + 1)
        } else {
            ubaksuyeol(n * 3 + 1, i + 1)
        }
    }
}