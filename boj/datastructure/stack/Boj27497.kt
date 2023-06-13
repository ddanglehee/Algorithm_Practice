class Boj27497 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val deque = ArrayDeque<Char>()
        val dStack = mutableListOf<Boolean>()

        repeat(n) {
            val input = readLine()

            when(input[0].digitToInt()) {
                1 -> {
                    deque.addLast(input[2])
                    dStack.add(false)
                }
                2 -> {
                    deque.addFirst(input[2])
                    dStack.add(true)
                }
                3 -> {
                    if (deque.isNotEmpty()) {
                        if (dStack.removeLast()) {
                            deque.removeFirst()
                        } else {
                            deque.removeLast()
                        }
                    }
                }
            }
        }

        if (deque.isEmpty()) {
            print(0)
        } else {
            print(deque.joinToString(""))
        }
    }
}