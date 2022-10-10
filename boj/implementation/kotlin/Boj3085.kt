class Boj3085 {

    fun main() = with (System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val candies = Array(n) { charArrayOf() }

        for (i in 0 until n) {
            candies[i] = readLine().toCharArray()
        }

        var result = 0

        val dy = intArrayOf(-1, 0, 1, 0)
        val dx = intArrayOf(0, 1, 0, -1)
        for (i in 0 until n) {
            for (j in 0 until n) {
                for (d in 0..3) {
                    val changeY = i +dy[d]
                    val changeX = j + dx[d]

                    if (changeY in 0 until n && changeX in 0 until n) {
                        swap(candies, i, j, changeY, changeX)
                        result = maxOf(result, countCandy(candies, i, j))
                        swap(candies, changeY, changeX, i, j)
                    }
                }
            }
        }

        println(result)
    }

    fun swap(candies: Array<CharArray>, i: Int, j: Int, k: Int, l:Int) {
        val tmp = candies[i][j]
        candies[i][j] = candies[k][l]
        candies[k][l] = tmp
    }

    fun countCandy(candies: Array<CharArray>, i: Int, j: Int): Int {
        var iMax = 1
        var jMax = 1

        var count = 1
        for (k in 1 until candies.size) {
            if (candies[i][k] == candies[i][k - 1]) {
                count++
                iMax = maxOf(iMax, count)
            } else {
                count = 1
            }
        }

        count = 1
        for (l in 1 until candies.size) {
            if (candies[l][j] == candies[l - 1][j]) {
                count++
                jMax = maxOf(jMax, count)
            } else {
                count = 1
            }
        }

        return maxOf(iMax, jMax)
    }
}