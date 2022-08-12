class FindPrimeNumber {

    private val answer = mutableSetOf<Int>()
    private val visited = BooleanArray(8)
    private val isPrime = BooleanArray(10000000) { true }

    fun solution(numbers: String): Int {
        initIsPrime()

        for (index in numbers.indices) {
            dfs(index, "", numbers)
        }

        return answer.size
    }

    private fun dfs(index: Int, current: String, numbers:String) {
        visited[index] = true
        val currentNumber = (current + numbers[index]).toInt()

        if (isPrime[currentNumber]) {
            answer.add(currentNumber)
        }

        for (idx in numbers.indices) {
            if (!visited[idx]) {
                dfs(idx, current + numbers[index], numbers)
            }
        }

        visited[index] = false
    }

    private fun initIsPrime() {
        isPrime[0] = false
        isPrime[1] = false
        for (i in 2..10000) {
            if (!isPrime[i]) continue

            for (j in (i * i) until 10000000 step i) {
                isPrime[j] = false
            }
        }
    }
}