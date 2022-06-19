import kotlin.math.max

class AntWarrior {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val warehouse = br.readLine().split(" ").map { it.toInt() }.toMutableList()

        warehouse[1] = max(warehouse[0], warehouse[1])
        for (i in 2..warehouse.lastIndex) {
            warehouse[i] = max(warehouse[i - 1], warehouse[i - 2] + warehouse[i])
        }

        println(warehouse[n - 1])
    }
}