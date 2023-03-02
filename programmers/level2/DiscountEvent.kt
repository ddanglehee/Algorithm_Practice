class DiscountEvent {
    fun solution(wants: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer = 0
        val buyArray = IntArray(number.size)
        val wantMap = mutableMapOf<String, Int>()
        init(wantMap, wants, discount, buyArray)
        if (canBuyAllOfWant(number, buyArray)) answer++

        for (day in 1..(discount.size - 10)) {

            wantMap[discount[day - 1]]?.let { buyArray[it]-- }
            wantMap[discount[day + 9]]?.let { buyArray[it]++ }

            if (canBuyAllOfWant(number, buyArray)) answer++
        }

        return answer
    }

    private fun init(wantMap: MutableMap<String, Int>, wants: Array<String>, discount: Array<String>, buyArray: IntArray) {
        wants.forEachIndexed { index, want ->
            wantMap[want] = index
        }

        for (d in 0 until 10) {
            val discountThing = discount[d]
            if (wantMap[discountThing] != null) {
                buyArray[wantMap[discountThing]!!]++
            }
        }
    }

    private fun canBuyAllOfWant(number: IntArray, buyArray: IntArray): Boolean {
        number.forEachIndexed { i, count ->
            if (count != buyArray[i]) return false
        }
        return true
    }
}