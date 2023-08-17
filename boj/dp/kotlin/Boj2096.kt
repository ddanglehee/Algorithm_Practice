class Boj2096 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val map = Array(n) {
            readLine().split(" ").map { it.toInt() }
        }

        val maxDp = Array(n) {
            IntArray(3)
        }
        val minDp = Array(n) {
            IntArray(3)
        }
        maxDp[0][0] = map[0][0]; maxDp[0][1] = map[0][1]; maxDp[0][2] = map[0][2]
        minDp[0][0] = map[0][0]; minDp[0][1] = map[0][1]; minDp[0][2] = map[0][2]
        for (i in 1 until n) {
            maxDp[i][0] = maxOf(maxDp[i - 1][0], maxDp[i - 1][1]) + map[i][0]
            maxDp[i][1] = maxOf(maxDp[i - 1][0], maxOf(maxDp[i - 1][1], maxDp[i - 1][2])) + map[i][1]
            maxDp[i][2] = maxOf(maxDp[i - 1][1], maxDp[i - 1][2]) + map[i][2]

            minDp[i][0] = minOf(minDp[i - 1][0], minDp[i - 1][1]) + map[i][0]
            minDp[i][1] = minOf(minDp[i - 1][0], minOf(minDp[i - 1][1], minDp[i - 1][2])) + map[i][1]
            minDp[i][2] =  minOf(minDp[i - 1][1], minDp[i - 1][2]) + map[i][2]
        }

        print("${maxOf(maxDp[n - 1][0], maxOf(maxDp[n - 1][1], maxDp[n - 1][2]))} ${minOf(minDp[n - 1][0], minOf(minDp[n - 1][1], minDp[n - 1][2]))}")
    }
}