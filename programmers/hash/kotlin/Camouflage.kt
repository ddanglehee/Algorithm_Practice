class Camouflage {
    fun solution(clothes: Array<Array<String>>): Int {
        var answer = 1
        val clothesMap = HashMap<String, Int>()

        clothes.forEach { c ->
            if (clothesMap.containsKey(c[1])) {
                clothesMap[c[1]] = (clothesMap[c[1]]?.plus(1))!!
            } else {
                clothesMap.put(c[1], 1)
            }
        }

        clothesMap.forEach { (key, _) ->
            answer *= (clothesMap[key]?.plus(1))!!
        }
        return answer - 1
    }
}