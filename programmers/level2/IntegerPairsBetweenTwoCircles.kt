class Solution {
    fun solution(r1: Int, r2: Int): Long {
        var answer: Long = 0

        var maxY = r2.toLong()
        var minY = r1.toLong()
        for (x in 0 until r2) {
            while (r2.toLong() * r2 < maxY * maxY + x.toLong() * x) maxY--
            while (0 < minY && r1.toLong() * r1 <= x.toLong() * x + minY * minY) minY--

            answer += maxY - minY
        }

        return answer * 4
    }
}