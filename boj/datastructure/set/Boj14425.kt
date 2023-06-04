class Boj14425 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map{ it.toInt() }
        val set = mutableSetOf<String>()

        repeat(n) {
            val string = readLine()
            set.add(string)
        }

        var answer = 0
        repeat(m) {
            val string = readLine()
            if (set.contains(string)) answer++
        }

        print(answer)
    }
}