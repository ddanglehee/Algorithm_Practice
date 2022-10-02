class Boj1541 {

    fun solution() = with (System.`in`.bufferedReader()) {
        val inputString = readLine()!!

        var isMinus = false
        var result = 0
        var tmpNumber = ""

        for (currentChar in inputString) {
            when (currentChar) {
                '+' -> {
                    if (isMinus) {
                        result -= tmpNumber.toInt()
                        tmpNumber = ""
                    } else {
                        result += tmpNumber.toInt()
                        tmpNumber = ""
                    }
                }
                '-' -> {
                    if (isMinus) {
                        result -= tmpNumber.toInt()
                        tmpNumber = ""
                    } else {
                        result += tmpNumber.toInt()
                        tmpNumber = ""
                        isMinus = true
                    }
                }
                else -> {
                    tmpNumber += currentChar
                }
            }
        }

        if (isMinus) {
            result -= tmpNumber.toInt()
        } else {
            result += tmpNumber.toInt()
        }

       println(result)
    }
}