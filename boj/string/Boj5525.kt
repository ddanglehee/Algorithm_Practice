class Boj5525 {

    fun main() = with (System.`in`.bufferedReader()) {
        val n = readLine()!!.toInt()
        val m = readLine()!!.toInt()
        val inputString = readLine()!!

        var result = 0
        var continuousIOICount = 0

        for (i in 0..(m - 3)) {
            if (inputString[i] == 'I') {
                if (inputString[i + 1] == 'O' && inputString[i + 2] == 'I') {
                    continuousIOICount++
                    if (n <= continuousIOICount) result++
                }
                else {
                    continuousIOICount = 0
                }
            }
        }

        println(result)
    }
}