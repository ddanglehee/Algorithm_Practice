class Boj15663 {

    val answerSet = mutableSetOf<String>()

    fun main() = with(System.`in`.bufferedReader()) {
        val (n, m) = readLine().split(" ").map { it.toInt() }
        val list = readLine().split(" ").map { it.toInt() }.sorted()

        val sequence = mutableListOf<Int>()
        val used = BooleanArray(list.size)
        for (i in list.indices) {
            used.fill(false)
            getSequence(list, i, m, sequence, used)
        }

        val sb = StringBuilder()
        answerSet.toMutableList().sort()
        for (sequence in answerSet) {
            sb.append(sequence).append("\n")
        }

        print(sb)
    }

    fun getSequence(list: List<Int>, cur: Int, count: Int, sequence: MutableList<Int>, used: BooleanArray) {
        used[cur] = true
        sequence.add(list[cur])

        if (sequence.size == count) {
            answerSet.add(sequence.joinToString(" "))
            used[cur] = false
            sequence.removeLast()
            return
        }

        for (i in list.indices) {
            if (used[i]) continue
            getSequence(list, i, count, sequence, used)
        }

        used[cur] = false
        sequence.removeLast()
    }
}