class Boj20922 {
    fun main() = with(System.`in`.bufferedReader()) {
        val (n, k) = readLine().split(" ").map { it.toInt() }
        val arr = readLine().split(" ").map { it.toInt() }

        var answer = 0
        var start = 0; var end = 0
        val count = IntArray(100001)
        count[arr[0]]++

        while (true) {
            if (k < count[arr[end]]) {
                count[arr[start]]--
                start++
            } else {
                answer = maxOf(answer, end - start + 1)
                end++
                if (end == n) break
                count[arr[end]]++
            }
        }

        print(answer)
    }
}