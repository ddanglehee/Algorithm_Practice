class Boj16206 {

    var m = 0

    fun main() = with(System.`in`.bufferedReader()) {
        var input = readLine().split(" ").map { it.toInt() }
        m = input[1]

        val rollCakeLengthList = mutableListOf<Int>()
        rollCakeLengthList.addAll(readLine().split(" ").map { it.toInt() })

        val cakeLengthMultiplesOf10 = rollCakeLengthList.filter { it % 10 == 0 }.sorted()
        val cakeLengthNotMultiplesOf10 = rollCakeLengthList.filter { it % 10 != 0 }

        var answer = 0
        answer += cutRollCake(cakeLengthMultiplesOf10, true)
        if (m != 0) {
            answer += cutRollCake(cakeLengthNotMultiplesOf10, false)
        }

        println(answer)
    }

    fun cutRollCake(rollCakeList: List<Int>, isMultiplesOf10: Boolean): Int {
        var rollCakeCount = 0

        for (cakeLength in rollCakeList) {
            val cutCount = if (isMultiplesOf10) cakeLength / 10 - 1 else cakeLength / 10
            if (cutCount <= m) {
                rollCakeCount += if (isMultiplesOf10) cutCount + 1 else cutCount
                m -= cutCount
            } else {
                rollCakeCount += m
                m = 0
                break
            }
        }

        return rollCakeCount
    }
}