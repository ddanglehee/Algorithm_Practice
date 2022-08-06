import java.util.*

class Printer {
    fun solution(priorities: IntArray, location: Int): Int {
        var answer = 0

        val queue = LinkedList<Document>()
        val priorityQueue = PriorityQueue<Int>(Collections.reverseOrder())
        priorities.forEachIndexed { index, priority ->
            queue.add(Document(index, priority))
            priorityQueue.add(priority)
        }

        while (queue.isNotEmpty()) {
            val currentPriority = priorityQueue.peek()

            val document = queue.removeFirst()
            if (currentPriority == document.priority) {
                priorityQueue.poll()
                answer++
                if (location == document.index) break
            } else {
                queue.add(document)
            }
        }

        return answer
    }

    // 틀린 풀이
    fun wrongSolution(priorities: IntArray, location: Int): Int {
        var answer = 0
        val pq = PriorityQueue(Comparator.comparing(Document::priority).reversed())

        priorities.forEachIndexed { index, priority ->
            pq.add(Document(priority, index))
        }

        while (pq.isNotEmpty()) {
            answer++
            val currentDocument = pq.poll()
            if (currentDocument.index == location) break
        }

        return answer
    }

    data class Document(val priority: Int, val index: Int)
}