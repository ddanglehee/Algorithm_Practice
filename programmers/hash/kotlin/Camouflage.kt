class Camouflage {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        val clothesMap = clothes.groupBy { it[1] }

        clothesMap.values.forEach {
            answer *= (it.size + 1)
        }
        return answer - 1
    }
}