class Solution {

    val answer = mutableListOf<Triple<Int, Int, Int>>()

    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {

        build_frame.forEach { (x, y, a, b) ->

            val target = Triple(x, y, a)
            if (b == 0) {
                answer.remove(target)
                if (!check()) answer.add(target)
            } else {
                answer.add(target)
                if (!check()) answer.removeLast()
            }
        }
        answer.sortWith(compareBy<Triple<Int, Int, Int>> { it.first }.thenBy { it.second }.thenBy { it.third })

        val arrAnswer = Array(answer.size) {
            intArrayOf(answer[it].first, answer[it].second, answer[it].third)
        }

        return arrAnswer
    }

    fun check(): Boolean {
        answer.forEach { (x, y, a) ->
            if (a == 0) {
                if (!(y == 0
                            || answer.contains(Triple(x, y, 1))
                            || answer.contains(Triple(x-1, y, 1))
                            || answer.contains(Triple(x, y-1, 0)))
                ) return false
            } else {
                if (!(answer.contains(Triple(x, y-1, 0))
                            || answer.contains(Triple(x+1, y-1, 0))
                            || (answer.contains(Triple(x-1, y, 1))
                            && answer.contains(Triple(x+1, y, 1))))
                ) return false
            }
        }
        return true
    }
}