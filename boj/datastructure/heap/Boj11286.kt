import java.util.PriorityQueue
import kotlin.math.*

class Boj11286 {

val priorityQueue = PriorityQueue(compareBy<Int> { abs(it) }.thenBy { it })

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()

    repeat(n) {

        when (val x = readLine().toInt()) {
            0 -> {
                println(pop())
            }
            else -> {
                insert(x)
            }
        }
    }
}

fun insert(x: Int) {
    priorityQueue.offer(x)
}

fun pop(): Int = if (priorityQueue.isEmpty()) {
    0
} else {
    priorityQueue.poll()
}

}