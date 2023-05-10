class Boj22233 {

        fun main() = with(System.`in`.bufferedReader()) {
            val (n, m) = readLine().split(" ").map { it.toInt() }
            val memoSet = mutableSetOf<String>()
            val usedSet = mutableSetOf<String>()
            val sb = StringBuilder()

            repeat(n) {
                val word = readLine()
                memoSet.add(word)
            }

            repeat(m) {
                val wordList = readLine().split(",")

                wordList.forEach { word ->
                    if (memoSet.contains(word)) {
                        usedSet.add(word)
                    }
                }

                sb.append(memoSet.size - usedSet.size).append("\n")
            }

            print(sb)
        }
}