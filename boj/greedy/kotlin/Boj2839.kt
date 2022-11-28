class Boj2839 {

    fun main() = with(System.`in`.bufferedReader()) {
        var n = readLine().toInt()

        var fiveCount = n / 5
        var remainder = n % 5

        while (fiveCount != 0) {
            if (remainder % 3 != 0) {
                fiveCount -= 1
                remainder += 5
            } else {
                break
            }
        }

        if (remainder % 3 == 0) {
            println(fiveCount + remainder / 3)
        } else {
            println(-1)
        }

    }
}