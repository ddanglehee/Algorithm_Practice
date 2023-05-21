import java.util.LinkedList

class Boj1138 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val list = readLine().split(" ").map { it.toInt() }

        val linkedList = LinkedList<Int>()
        linkedList.add(n)
        for (i in list.lastIndex - 1 downTo 0) {
            linkedList.add(list[i], i + 1)
        }

        print(linkedList.joinToString(" "))
    }
}