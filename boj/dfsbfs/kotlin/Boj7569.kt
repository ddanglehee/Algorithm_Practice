class Boj7569 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (m, n, h) = readLine().split(" ").map { it.toInt() }
        val boxes = Array(h) {
            Array(n) {
                IntArray(m)
            }
        }
        val queue = ArrayDeque<Pair<Int, Triple<Int, Int, Int>>>()

        for (i in 0 until h) for (j in 0 until n) {
            val input = readLine().split(" ").map { it.toInt() }

            for (k in input.indices) {
                val info = input[k]
                boxes[i][j][k] = info

                if (info == 1) {
                    queue.add(Pair(0, Triple(i, j, k)))
                }
            }
        }

        val dh = intArrayOf(1, -1, 0, 0, 0, 0)
        val dr = intArrayOf(0, 0, 1, -1, 0, 0)
        val dc = intArrayOf(0, 0, 0, 0, 1, -1)
        var answer = 0
        while (queue.isNotEmpty()) {
            val (day, location) = queue.removeFirst()

            answer = maxOf(answer, day)

            for (d in 0 until 6) {
                val nH = location.first + dh[d]
                val nR = location.second + dr[d]
                val nC = location.third + dc[d]

                if (nH in 0 until h && nR in 0 until n && nC in 0 until m && boxes[nH][nR][nC] == 0) {
                    boxes[nH][nR][nC] = 1
                    queue.add(Pair(day + 1, Triple(nH, nR, nC)))
                }
            }
        }

        for (i in 0 until h) for (j in 0 until n) for(k in 0 until m) {
            if (boxes[i][j][k] == 0) {
                print(-1)
                return
            }
        }

        print(answer)
    }
}