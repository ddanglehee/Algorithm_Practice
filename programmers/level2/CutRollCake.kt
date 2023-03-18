class CutRollCake {
    fun solution(topping: IntArray): Int {
        var answer: Int = 0;
        val toppingLastIndex = topping.lastIndex
        val count1 = IntArray(topping.size); count1[0] = 1
        val used1 = BooleanArray(10001); used1[topping[0]] = true
        val count2 = IntArray(topping.size); count2[toppingLastIndex] = 1
        val used2 = BooleanArray(10001); used2[topping[toppingLastIndex]] = true

        for (i in 1 until topping.lastIndex) {
            if (used1[topping[i]]) {
                count1[i] = count1[i - 1]
            } else {
                count1[i] = count1[i - 1] + 1
                used1[topping[i]] = true
            }
            if (used2[topping[toppingLastIndex - i]]) {
                count2[toppingLastIndex - i] = count2[toppingLastIndex - i + 1]
            } else {
                count2[toppingLastIndex - i] = count2[toppingLastIndex - i + 1] + 1
                used2[topping[toppingLastIndex - i]] = true
            }
        }

        for (i in 0 until topping.lastIndex) {
            if (count1[i] == count2[i + 1]) answer++
        }

        return answer
    }
}