class Boj1935 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val alphabetToNumber = Array(26) { 0.0 }
        val postfix = readLine()

        for (i in 0 until n) {
            alphabetToNumber[i] = readLine().toDouble()
        }

        val stack = mutableListOf<Double>()
        for (op in postfix) {
            if (op.isLetter()) {
                stack.add(alphabetToNumber[op - 'A'])
            } else {
                val b = stack.removeLast()
                val a = stack.removeLast()
                stack.add(calculate(a, b, op))
            }
        }

        println(String.format("%.2f", stack.removeLast()))
    }

    fun calculate(a: Double, b: Double, operator: Char): Double {
        return  when (operator) {
            '+' -> {
                a + b
            }
            '-' -> {
                a - b
            }
            '*' -> {
                a * b
            }
            else -> {
                a / b
            }
        }
    }
}