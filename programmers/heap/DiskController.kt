import java.util.*

class DiskController {
    fun solution(jobs: Array<IntArray>): Int {
        var answer = 0
        jobs.sortBy { it[0] }

        val priorityQueue = PriorityQueue<IntArray>(compareBy { it[1] })
        priorityQueue.add(jobs[0])
        var t = jobs[0][0]
        var i = 1

        while (i < jobs.size || priorityQueue.isNotEmpty()) {
            // t시간, 그 이전에 들어온 요청을 큐에 넣기
            while (i < jobs.size && jobs[i][0] <= t) {
                priorityQueue.add(jobs[i++])
            }

            if (priorityQueue.isNotEmpty()) {
                val currentJob = priorityQueue.poll()
                answer += (t - currentJob[0] + currentJob[1]) // 현재 작업 응답 시간 answer에 더하기
                t += currentJob[1]
            } else {
                t++
            }
        }

        return answer / jobs.size
    }
}