class Boj25918 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val s = readLine()

        val stack = mutableListOf<Char>()
        var answer = 0
        var count = 1
        for (c in s) {
            if (stack.isEmpty()) {
                count = 1
                stack.add(c)
            } else {
                if (stack.last() != c) {
                    stack.removeLast()
                    answer = maxOf(answer, count--)
                } else {
                    count++
                    stack.add(c)
                }
            }
        }

        if (stack.isNotEmpty()) print(-1) else print(answer)
    }
}