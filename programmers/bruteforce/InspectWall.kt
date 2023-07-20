class Solution {

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        var answer = dist.size + 1

        val weakSet = mutableSetOf<Int>()
        for (i in weak.indices) {
            for (j in weak.indices) {
                if (weak.lastIndex < i + j) {
                    weakSet.add(weak[(i + j) % weak.size] + n)
                } else {
                    weakSet.add(weak[i + j])
                }
            }
        }
        val newWeak = weakSet.toList().sorted()
        val permutationList = permutation(dist.toList().sortedDescending())

        for (startIdx in weak.indices) {
            var count = 0
            var fixed = weak[startIdx]
            val end = newWeak[startIdx + weak.lastIndex]
            for (perm in permutationList) {
                fixed = weak[startIdx]
                for (d in perm) {
                    fixed += d
                    count++
                    if (end <= fixed) {
                        answer = minOf(answer, count)
                        break
                    } else {
                        for (i in startIdx..startIdx + weak.lastIndex) {
                            if (newWeak[i] <= fixed) continue
                            fixed = newWeak[i]
                            break
                        }
                    }
                }
                count = 0
            }
        }

        if (answer == dist.size + 1) answer = -1
        return answer
    }

    fun permutation(el: List<Int>, fin: List<Int> = listOf(), sub: List<Int> = el): List<List<Int>> {
        return if (sub.isEmpty()) {
            listOf(fin)
        } else {
            sub.flatMap { permutation(el, fin + it, sub - it) }
        }
    }
}