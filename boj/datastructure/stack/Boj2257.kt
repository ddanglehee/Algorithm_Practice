class Boj2257 {

    fun main() = with(System.`in`.bufferedReader()) {
        val input = readLine()

        val stack = mutableListOf<Int>()
        val map = mutableMapOf('H' to 1, 'C' to 12, 'O' to 16, '(' to 0)

        input.forEach {
            if (it == ')') {
                var tmp = 0
                while (true) {
                    val top = stack.removeLast()
                    tmp += top

                    if (top == 0) break
                }
                stack.add(tmp)
            } else if (it.isDigit()) {
                stack.add(stack.removeLast() * it.digitToInt())
            } else {
                stack.add(map[it]!!)
            }
        }

        var answer = 0
        while (stack.isNotEmpty()) {
            answer += stack.removeLast()
        }

        print(answer)
    }
}