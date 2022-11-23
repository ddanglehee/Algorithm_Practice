class Boj20302 {

    val MAX = 100001

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine()
        val expression = readLine().split(" ")

        if ("0" in expression) {
            println("mint chocolate")
            return
        }

        val isPrime = BooleanArray(MAX) { true }
        val primeCount = IntArray(MAX)
        setPrimeArray(isPrime)

        factorization(isPrime, primeCount, expression[0].toInt(), 1)
        for (i in 1 until expression.size step 2) {
            when (expression[i]) {
                "*" -> factorization(isPrime, primeCount, expression[i + 1].toInt(), 1)
                "/" -> factorization(isPrime, primeCount, expression[i + 1].toInt(), -1)
            }
        }

        var isInteger = true
        primeCount.forEach {
            if (it < 0) {
                isInteger = false
                return@forEach
            }
        }

        if (isInteger) {
            println("mint chocolate")
        } else {
            println("toothpaste")
        }
    }

    fun factorization(isPrime: BooleanArray, primeCount: IntArray, operand: Int, operator: Int) {
        var operandTmp = operand
        for (i in 2..MAX) {
            if (!isPrime[i]) continue
            while (operandTmp % i == 0) {
                operandTmp /= i
                primeCount[i] += operator
            }
            if (operandTmp / i == 0) break
        }
    }

    fun setPrimeArray(isPrime: BooleanArray) {
        isPrime[0] = false; isPrime[1] = false

        for (i in 2 until MAX) {
            if (isPrime[i]) {
                var j = i
                while (i * j in 1 until MAX) {
                    isPrime[i * j] = false
                    j++
                }
            }
        }
    }
}