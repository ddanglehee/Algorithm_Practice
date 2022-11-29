class Boj2231 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        var answer = 0
        for (i in n - n.toString().length * 9..n) {
            if (n == decomposition(i)) {
                answer = i
                break
            }
        }

        println(answer)
    }

    fun decomposition(number: Int): Int {
        var result = number
        val numberInString = number.toString()

        for (n in numberInString) {
            result += n - '0'
        }

        return result
    }
}