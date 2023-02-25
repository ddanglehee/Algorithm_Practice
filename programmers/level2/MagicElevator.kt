class MagicElevator {
    fun solution(storey: Int): Int {
        var answer: Int = 0
        val storeyString = storey.toString()

        var addition = 0
        for (i in storeyString.length - 1 downTo 0) {
            val n = storeyString[i] - '0' + addition

            if (n == 10) {
                continue
            } else if (5 < n || (n == 5 && i != 0 && 5 <= storeyString[i - 1] - '0')) {
                answer += (10 - n)
                addition = 1
            } else {
                answer += n
                addition = 0
            }
        }

        return answer + addition
    }
}