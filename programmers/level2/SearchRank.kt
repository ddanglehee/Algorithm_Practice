class Solution {

    private val conditionMap = mutableMapOf<String, MutableList<Int>>()

    fun solution(infos: Array<String>, queries: Array<String>): IntArray {

        val answer = IntArray(queries.size)

        init()

        infos.forEach { info ->
            val (lang, job, career, food, score) = info.split(" ")
            arrayOf(lang, "-").forEach { l ->
                arrayOf(job, "-").forEach { j ->
                    arrayOf(career, "-").forEach { c ->
                        arrayOf(food, "-").forEach { f ->
                            conditionMap[l + j + c + f]!!.add(score.toInt())
                        }
                    }
                }
            }
        }

        conditionMap.forEach { key, _ ->
            conditionMap[key]!!.sortDescending()
        }

        queries.forEachIndexed { index, query ->
            val (lang, job, career, foodScore) = query.split(" and ")
            val (food, score) = foodScore.split(" ")

            answer[index] = binarySearch(conditionMap[lang + job + career + food]!!, score.toInt())
        }

        return answer
    }

    private fun init() {
        arrayOf("cpp", "java", "python", "-").forEach { lang ->
            arrayOf("backend", "frontend", "-").forEach { job ->
                arrayOf("junior", "senior", "-").forEach { career ->
                    arrayOf("chicken", "pizza", "-").forEach { food ->
                        conditionMap[lang + job + career + food] = mutableListOf<Int>()
                    }
                }
            }
        }
    }

    private fun binarySearch(list: MutableList<Int>, target: Int): Int {
        var start = 0
        var end = list.lastIndex
        var mid: Int

        while (start <= end) {
            mid = (start + end) / 2
            if (list[mid] < target) {
                end = mid - 1
            } else {
                start = mid + 1
            }
        }

        return start
    }
}