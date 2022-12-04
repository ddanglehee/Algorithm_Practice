class Boj1253 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val numbers = readLine().split(" ").map { it.toInt() }.sorted()

        var answer = 0
        for (i in numbers.indices) {
            if (isGood(numbers, i)) {
                answer++
            }
        }

        println(answer)
    }

    fun isGood(numbers: List<Int>, targetIndex: Int): Boolean {
        var i = 0; var j = numbers.lastIndex

        while (i < j) {
            if (i == targetIndex) {
                i++
                continue
            }
            if (j == targetIndex) {
                j--
                continue
            }
            val tmpSum = numbers[i] + numbers[j]

            if (tmpSum < numbers[targetIndex]) {
                i++
            } else if (tmpSum == numbers[targetIndex]) {
                return true
            } else {
                j--
            }
        }

        return false
    }
}