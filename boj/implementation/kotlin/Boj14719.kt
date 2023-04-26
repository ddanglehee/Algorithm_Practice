class Boj14719 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (h, w) = readLine().split(" ").map { it.toInt() }
        val block = readLine().split(" ").map { it.toInt() }

        var leftMaxIdx = 0
        var rightMaxIdx = findRightMaxIndex(block, 1)

        var answer = 0
        for (i in 1 until block.lastIndex) {
            val height = minOf(block[leftMaxIdx], block[rightMaxIdx])

            if (height > block[i]) {
                answer += (height - block[i])
            }

            if (block[leftMaxIdx] < block[i]) {
                leftMaxIdx = i
            }
            if (rightMaxIdx == i) {
                rightMaxIdx = findRightMaxIndex(block, i + 1)
            }
        }

        print(answer)
    }

    fun findRightMaxIndex(block: List<Int>, start: Int): Int {
        if (start == block.lastIndex) return block.lastIndex

        var max = 0
        var rightMaxIdx = block.lastIndex
        for (j in start..block.lastIndex) {
            if (max < block[j]) {
                max = block[j]
                rightMaxIdx = j
            }
        }
        return rightMaxIdx
    }
}