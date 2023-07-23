fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val list = readLine().split(" ").map { it.toInt() }
    val answer = IntArray(n) { -1 }

    for (i in list.lastIndex - 1 downTo 0) {
        if (list[i] < list[i + 1]) {
            answer[i] = list[i + 1]
        } else {
            for (j in i + 1..list.lastIndex) {
                if (list[i] < answer[j]) {
                    answer[i] = answer[j]
                    break
                }
            }
        }
    }

    print(answer.joinToString(" "))
}