class BiggestNumber {
    fun solution(numbers: IntArray): String {
        var answer = ""
        val numbersList = numbers.map { Number(it.toString()) }
        numbersList.forEach { number: Number ->
            var priority = number.string
            while (3 < priority.length) {
                priority += number.string
            }
            number.priority = priority
        }
        numbersList.sortedBy { it.priority }.reversed()
        numbersList.forEach { number ->
            answer += number.string
        }
        return answer
    }

    data class Number(val string: String) {
        lateinit var priority: String
    }
}