class MockExam {

    fun solution(answers: IntArray): IntArray {
        var answer = mutableListOf<Int>()
        var scores = IntArray(3)
        val submit1 = intArrayOf(1, 2, 3, 4, 5)
        val submit2 = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5)
        val submit3 = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5)

        answers.forEachIndexed { index, number ->
            if (submit1[index % 5] == number) scores[0]++
            if (submit2[index % 8] == number) scores[1]++
            if (submit3[index % 10] == number) scores[2]++
        }

        val maxScore = scores.maxOrNull()
        scores.forEachIndexed { index, score ->
            if (maxScore == score) {
                answer.add(index + 1)
            }
        }

        return answer.toIntArray()
    }
}