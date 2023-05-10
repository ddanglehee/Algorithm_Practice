class Boj20920 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val wordMap = mutableMapOf<String, Int>()

        repeat(n) {
            val input = readLine()
            if (m <= input.length) {
                wordMap[input] = (wordMap[input] ?: 0) + 1
            }
        }
        val words = wordMap.keys.toMutableList()
        words.sortWith(compareByDescending<String> { wordMap[it] }.thenByDescending { it.length }.thenBy { it } )

        val sb = StringBuilder()
        words.forEach { sb.append(it).append("\n") }
        print(sb)
    }
}
