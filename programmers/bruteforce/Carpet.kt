class Carpet {

    fun solution(brown: Int, yellow: Int): IntArray {
        var answer = IntArray(2)

        if (yellow == 1) {
            answer[0] = 3
            answer[1] = 3
        }

        for (i in 1..(yellow / 2)) {
            if (yellow % i != 0) continue
            val j = yellow / i

            val expectedBrown = (i + j) * 2 + 4
            if (expectedBrown == brown) {
                answer[0] = j + 2
                answer[1] = i + 2
                break
            }
        }

        return answer
    }
}