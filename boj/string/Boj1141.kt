class Boj1141 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val words = mutableListOf<String>()

        repeat(n) {
            words.add(readLine())
        }

        words.sortBy { it.length }
        var answer = 0
        for (i in 0 until n) {
            var flag = true
            for (j in i + 1 until n) {
                if (words[j].startsWith(words[i])) {
                    flag = false
                    break
                }
            }
            if (flag) answer++
        }

        print(answer)
    }
}