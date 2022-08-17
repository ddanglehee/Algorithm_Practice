class TargetNumber {

    private lateinit var numbers: IntArray
    private var target = 0

    fun solution(numbers: IntArray, target: Int): Int {
        this.numbers = numbers
        this.target = target

        return dfs(0, -1)
    }

    private fun dfs(sum: Int, index: Int): Int {
        if (index == numbers.lastIndex) {
            return if (target == sum) {
                1
            } else {
                0
            }
        }

        val nextIndex = index + 1
        return dfs(sum + numbers[nextIndex], nextIndex) + dfs(sum - numbers[nextIndex], nextIndex)
    }
}