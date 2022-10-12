class MakePrime {

    fun solution(nums: IntArray): Int {
        var answer = 0
        val isPrime = BooleanArray(50000) { true }

        // 1. 에라토스테네스의 체
        isPrime[0] = false; isPrime[1] = false
        for (i in 2 until 50000) {
            if (!isPrime[i]) continue
            var j = 2

            while (i * j < 50000) {
                isPrime[i * j] = false
                j++
            }
        }

        // 2. 3개 수 골라서 더하기
        for (i in 0..(nums.size - 3)) {
            for (j in (i + 1)..(nums.size - 2)) {
                for (k in (j + 1) until nums.size) {
                    if (isPrime[nums[i] + nums[j] + nums[k]]) {
                        answer++
                    }
                }
            }
        }

        return answer
    }
}