class Boj15649 {

    val tmpSequenceList = mutableListOf<Int>()
    val used = BooleanArray(9)

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }

        sequence(n, m, 1)
    }

    fun sequence(n: Int, m: Int, curCount: Int) {
        for (i in 1..n) {
            if (used[i]) continue
            tmpSequenceList.add(i)
            used[i] = true
            if (curCount == m) {
                println(tmpSequenceList.joinToString(" "))
            } else {
                sequence(n, m, curCount + 1)
            }
            tmpSequenceList.removeLast()
            used[i] = false
        }
    }
}