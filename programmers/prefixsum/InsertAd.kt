class Solution {
    fun solution(play_time: String, adv_time: String, logs: Array<String>): String {
        val playTime = play_time.split(":").map { it.toInt() }.run {
            this[0] * 3600 + this[1] * 60 + this[2]
        }
        val arr = IntArray(playTime + 2)

        logs.forEach { log ->
            val (start, end) = log.split("-")
            val (startH, startM, startS) = start.split(":").map { it.toInt() }
            val startTime = startH * 3600 + startM * 60 + startS
            val (endH, endM, endS) = end.split(":").map { it.toInt() }
            val endTime = endH * 3600 + endM * 60 + endS

            arr[startTime]++
            arr[endTime]--
        }

        var sum = arr[0].toLong()
        val advTime = adv_time.split(":").map { it.toInt() }.run {
            this[0] * 3600 + this[1] * 60 + this[2]
        }
        for (end in 1 until advTime) {
            arr[end] += arr[end - 1]
            sum += arr[end]
        }

        var maxSum = sum
        var answer = 0
        var start = 0
        for (end in advTime..playTime) {
            arr[end] += arr[end - 1]

            sum -= arr[start++]
            sum += arr[end]
            if (maxSum < sum) {
                answer = start
                maxSum = sum
            }
        }

        return answer.toTimeFormat()
    }

    private fun Int.toTimeFormat(): String {
        val sb = StringBuilder()
        val h = this / 3600
        val m = this % 3600 / 60
        val s = this % 3600 % 60

        if (h < 10) sb.append("0")
        sb.append(h).append(":")
        if (m < 10) sb.append("0")
        sb.append(m).append(":")
        if (s < 10) sb.append("0")
        sb.append(s)

        return sb.toString()
    }
}