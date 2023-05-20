class Boj14426 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val s = mutableListOf<String>()

        var input: String
        for (i in 0 until n) {
            input = readLine()
            s.add(input)
        }
        s.sort()

        var answer = 0
        var prefix: String
        var start: Int; var end: Int
        for (i in 0 until m) {
            prefix = readLine()

            start = 0; end = s.lastIndex
            while (start <= end) {
                val mid = (start + end) / 2

                if (prefix <= s[mid]) {
                    if (s[mid].startsWith(prefix)) {
                        answer++
                        break
                    }
                    end = mid - 1
                } else {
                    start = mid + 1
                }
            }
        }

        print(answer)
    }
}