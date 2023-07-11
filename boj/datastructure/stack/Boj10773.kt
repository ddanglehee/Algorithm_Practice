class Boj10773 {

    fun main() = with(System.`in`.bufferedReader()) {
        val k = readLine().toInt()

        val stack = mutableListOf<Int>()

        repeat(k) {
            val input = readLine().toInt()

            if (input == 0) {
                stack.removeLast()
            } else {
                stack.add(input)
            }
        }

        print(stack.sum())
    }
}