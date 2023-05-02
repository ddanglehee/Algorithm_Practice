class Solution {
    fun solution(line: Array<IntArray>): Array<String> {
        var minX = Long.MAX_VALUE; var maxX = Long.MIN_VALUE
        var minY = Long.MAX_VALUE; var maxY = Long.MIN_VALUE
        val star = mutableListOf<Pair<Long, Long>>()

        // 1. 교점 찾기
        for (i in line.indices) {
            val (a, b, e) = line[i].map { it.toLong() }
            for (j in (i+1)..line.lastIndex) {
                val (c, d, f) = line[j].map { it.toLong() }

                if (a * d - b * c == 0L
                    || (e * c - a * f) % (a * d - b * c) != 0L || (b * f - e * d) % (a * d - b * c) != 0L) continue
                val y = (e * c - a * f) / (a * d - b * c)
                val x = (b * f - e * d) / (a * d - b * c)
                star.add(Pair(y, x))
                minX = minOf(minX, x); maxX = maxOf(maxX, x)
                minY = minOf(minY, y); maxY = maxOf(maxY, y)
            }
        }

        // 2. 모든 별을 포함하는 최소 사각형 구하기
        val rect = Array((maxY - minY + 1).toInt()) {
            CharArray((maxX - minX + 1).toInt()) {
                '.'
            }
        }
        for (point in star) {
            rect[(-1) * (point.first - maxY).toInt()][(point.second - minX).toInt()] = '*'
        }
        val answer = rect.map { it.joinToString("") }.toTypedArray()

        return answer
    }
}