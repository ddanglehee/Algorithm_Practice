import kotlin.math.abs

class Boj2470 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val list = readLine().split(" ").map { it.toInt() }.sorted()

        var closest = 2000000000
        val answer = IntArray(2)
        var s = 0; var e = list.lastIndex

        while (s < e) {
            val sum = list[e] + list[s]
            if (abs(sum) < closest) {
                closest = abs(sum)
                answer[0] = list[s]
                answer[1] = list[e]
            }
            if (sum < 0) {
                s++
            } else if (sum == 0) {
                break
            } else {
                e--
            }
        }

        print("${answer[0]} ${answer[1]}")
    }
}