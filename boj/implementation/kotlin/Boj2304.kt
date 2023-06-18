class Boj2304 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val heightInfo = IntArray(1001)
        var start = 1000
        var end = 1
        var maxHeight = 0
        repeat(n) {
            val (l, h) = readLine().split(" ").map { it.toInt() }
            heightInfo[l] = h
            start = minOf(start, l)
            end = maxOf(end, l)
            maxHeight = maxOf(maxHeight, h)
        }

        var answer = heightInfo[start]
        var height = heightInfo[start++]
        for (i in start..end) {
            height = maxOf(height, heightInfo[i])
            answer += height

            if (maxHeight == height) {
                start = i + 1

                break
            }
        }

        height = 0
        for (i in end downTo start) {
            height = maxOf(height, heightInfo[i])
            answer += height

        }

        print(answer)
    }
}