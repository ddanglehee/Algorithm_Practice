class GameDevelopment {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        var (a, b, d) = br.readLine().split(" ").map { it.toInt() }

        // idx : 북(0), 동(1), 남(2), 서(3)
        val forward = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))

        val map = mutableListOf<MutableList<Int>>()
        for (i in 0 until n) {
            val tmp = br.readLine().split(" ").map { it.toInt() }.toMutableList()
            map.add(tmp)
        }

        var result = 1
        map[a][b] = 1
        while (true) {
            var move = false

            for (i in 0 until 4) {
                d = turnLeft(d)
                val tmpA = a + forward[d].first
                val tmpB = b + forward[d].second

                if (map[tmpA][tmpB] == 0) {
                    a = tmpA
                    b = tmpB
                    move = true
                    result++
                    map[a][b] = 1
                    break
                }
            }

            if (!move) {
                val tmpA = a - forward[d].first
                val tmpB = b - forward[d].second

                if (map[tmpA][tmpB] == 0) {
                    a = tmpA
                    b = tmpB
                    result++
                    map[a][b] = 1
                } else {
                    break
                }
            }
        }

        println(result)
    }

    private fun turnLeft(d: Int): Int {
        return if (d == 0) 3 else d - 1
    }
}