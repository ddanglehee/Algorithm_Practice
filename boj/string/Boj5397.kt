import java.util.LinkedList

class Boj5397 {

    fun main() = with(System.`in`.bufferedReader()) {
        val T = readLine().toInt()
        val sb = StringBuilder()
        val answerList = LinkedList<Char>()

        repeat(T) {
            val input = readLine()
            answerList.clear()
            var cursor = 0

            for (c in input) {
                when(c) {
                    '<' -> {
                        if (cursor != 0) {
                            cursor--
                        }
                    }
                    '>' -> {
                        if (cursor != answerList.size) {
                            cursor++
                        }
                    }
                    '-' -> {
                        if (0 < cursor) answerList.removeAt(--cursor)
                    }
                    else -> {
                        answerList.add(cursor++, c)
                    }
                }
            }

            sb.append(answerList.joinToString("")).append("\n")
        }

        print(sb)
    }
}