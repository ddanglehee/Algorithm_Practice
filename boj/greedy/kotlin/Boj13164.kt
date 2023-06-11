class Boj13164 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        if (n == k) {
            print(0)
            return
        }

        val children = readLine().split(" ").map { it.toInt() }
        val diff = IntArray(n - 1)

        for (i in 1 until children.size) {
            diff[i - 1] = children[i] - children[i - 1]
        }

        diff.sortDescending()
        var answer = 0
        for (i in k - 1..diff.lastIndex) {
            answer += diff[i]
        }

        print(answer)
    }
}