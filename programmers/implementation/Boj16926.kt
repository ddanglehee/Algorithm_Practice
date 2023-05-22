class Boj16926 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m, r) = readLine().split(" ").map { it.toInt() }
        val arr = Array(n) {
            readLine().split(" ").toTypedArray()
        }
        val c = minOf(n, m) / 2
        repeat(r) {
            rotate(arr, c)
        }

        for (i in 0 until n) {
            for (j in 0 until m) {
                print("${arr[i][j]} ")
            }
            println()
        }
    }

    fun rotate(arr: Array<Array<String>>, count: Int) {

        for (cnt in 0 until count) {
            val tmp = arr[cnt][cnt]

            val r = arr.lastIndex - cnt
            val c = arr[0].lastIndex - cnt

            // 상
            for (j in cnt + 1..c) {
                arr[cnt][j - 1] = arr[cnt][j]
            }

            // 우
            for (i in cnt + 1..r) {
                arr[i - 1][c] = arr[i][c]
            }

            // 하
            for (j in c - 1 downTo cnt) {
                arr[r][j + 1] = arr[r][j]
            }

            // 좌
            for (i in r - 1 downTo cnt) {
                arr[i + 1][cnt] = arr[i][cnt]
            }

            arr[cnt + 1][cnt] = tmp
        }
    }
}