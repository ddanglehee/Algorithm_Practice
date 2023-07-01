class Boj2529 {

    var max = "0"
    var min = "9999999999"

    fun main() = with(System.`in`.bufferedReader()) {
        val k = readLine().toInt()
        val li = readLine().split(" ")

        var usedSequence: IntArray
        for (start in 9 downTo 0) {
            usedSequence = IntArray(10) { -1 }
            solution(start, 0, usedSequence, li, k)
        }

        println(max)
        print(min)
    }

    fun solution(num: Int, sequence: Int, usedSequence: IntArray, li: List<String>, k: Int) {
        usedSequence[num] = sequence

        if (sequence == k) {
            getAnswer(usedSequence, k)
        } else {
            for (nNum in 0..9) {
                if (usedSequence[nNum] != -1) continue

                when (li[sequence]) {
                    ">" -> {
                        if (num > nNum) solution(nNum, sequence + 1, usedSequence, li, k)
                    }
                    "<" -> {
                        if (num < nNum) solution(nNum, sequence + 1, usedSequence, li, k)
                    }
                }
            }
        }

        usedSequence[num] = -1
    }

    fun getAnswer(usedSequence: IntArray, k: Int) {
        val sb = StringBuilder()

        for (sequence in 0..k) {
            for (i in 0..9) {
                if (usedSequence[i] == sequence) sb.append(i)
            }
        }

        if (max.toLong() < sb.toString().toLong()) {
            max = sb.toString()
        }
        if (sb.toString().toLong() < min.toLong()) {
            min = sb.toString()
        }
    }
}