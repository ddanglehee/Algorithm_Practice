class Boj7490 {

    val sb = StringBuilder()
    val operatorList = mutableListOf<Char>()

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()

        for (i in 1..t) {
            val n = readLine().toInt()
            solution(0, n)
            if (i != t) sb.append("\n")
        }

        print(sb)
    }

    fun solution(cur: Int, n: Int) {
        if (cur == n - 1) {
            var result = 1
            var i = 0
            while (i < n - 1) {
                if (operatorList[i] == ' ') {
                    result *= 10
                    result += (i++ + 2)
                } else {
                    break
                }
            }

            if (i == operatorList.size) return

            var operator = operatorList[i]
            i += 2
            var op2 = i
            for (j in i - 1 until n - 1) {
                when (operatorList[j]) {
                    '+', '-' -> {
                        if (operator == '+') {
                            result += op2
                        } else {
                            result -= op2
                        }
                        operator = operatorList[j]
                        op2 = ++i
                    }
                    ' ' -> {
                        op2 *= 10
                        op2 += ++i
                    }
                }
            }

            if (operator == '+') {
                result += op2
            } else {
                result -= op2
            }

            if (result == 0) {
                for(i in 1 until n) {
                    sb.append(i).append(operatorList[i - 1])
                }
                sb.append(n).append("\n")
            }
            return
        }

        operatorList.add(' ')
        solution(cur + 1, n)
        operatorList.removeLast()
        operatorList.add('+')
        solution(cur + 1, n)
        operatorList.removeLast()
        operatorList.add('-')
        solution(cur + 1, n)
        operatorList.removeLast()
    }
}