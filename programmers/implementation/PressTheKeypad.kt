import kotlin.math.abs

class PressTheKeypad {

    fun solution(numbers: IntArray, hand: String): String {
        var answer = ""


        val numberLocationMap = mapOf<Int, Pair<Int, Int>>(1 to Pair(0, 0), 2 to Pair(0, 1), 3 to Pair(0, 2), 4 to Pair(1, 0), 5 to Pair(1, 1), 6 to Pair(1, 2), 7 to Pair(2, 0), 8 to Pair(2, 1), 9 to Pair(2, 2), 0 to Pair(3, 1))

        var leftHand = Pair(3, 0)
        var rightHand = Pair(3, 2)

        numbers.forEach { number ->
            when (number) {
                1, 4, 7 -> {
                    answer += "L"
                    leftHand = numberLocationMap[number]!!
                }
                3, 6, 9 -> {
                    answer += "R"
                    rightHand = numberLocationMap[number]!!
                }
                2, 5, 8, 0 -> {
                    val numberLocation = numberLocationMap[number]!!
                    val leftDiff = abs(numberLocation.first - leftHand.first) + abs(numberLocation.second - leftHand.second)
                    val rightDiff = abs(numberLocation.first - rightHand.first) + abs(numberLocation.second - rightHand.second)

                    if (leftDiff < rightDiff || (leftDiff == rightDiff && hand == "left")) {
                        answer += "L"
                        leftHand = numberLocationMap[number]!!
                    } else {
                        answer += "R"
                        rightHand = numberLocationMap[number]!!
                    }
                }
            }
        }

        return answer
    }
}