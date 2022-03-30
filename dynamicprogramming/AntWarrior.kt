import kotlin.math.max

class AntWarrior {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val warehouse = br.readLine().split(" ").map { it.toInt() }.toMutableList()

        var max = max(warehouse[0], warehouse[1])
        for (i in 2..warehouse.lastIndex) {
            if (warehouse[i - 2] + warehouse[i] < max) {
                warehouse[i] = max
            } else {
                warehouse[i] = warehouse[i - 2] + warehouse[i]
                max = warehouse[i]
            }
        }

        println(max)
    }
}