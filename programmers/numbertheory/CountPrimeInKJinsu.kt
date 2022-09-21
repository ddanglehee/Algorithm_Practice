import kotlin.math.sqrt

class CountPrimeInKJinsu {
    fun solution(n: Int, k: Int): Int {
        val newN = makeKJinsu(n, k)
        return getPrimeCount(newN)
    }

    private fun makeKJinsu(n: Int, k: Int): String {
        var result = ""
        var n = n

        while (n != 0) {
            result = (n % k).toString() + result
            n /= k
        }

        return result
    }

    private fun getPrimeCount(n: String): Int {
        var result = 0
        var tmpNumber = ""

        for (i in n.indices) {
            val currentNum = n[i].digitToInt()
            if (currentNum == 0 && tmpNumber != "") {
                if (isPrime(tmpNumber.toLong())) {
                    result++
                }
                tmpNumber = ""
            } else {
                tmpNumber += currentNum.toString()
            }
        }

        if (tmpNumber != "" && isPrime(tmpNumber.toLong())) result++

        return result
    }

    private fun isPrime(n: Long): Boolean {
        if (n == 0L || n == 1L) return false

        for (i in 2 until sqrt(n.toDouble()).toInt() + 1) {
            if (n % i == 0L) {
                return false
            }
        }
        return true
    }
}