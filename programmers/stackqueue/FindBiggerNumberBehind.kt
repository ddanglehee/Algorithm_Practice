class FindBiggerNumberBehind {
    fun solution(numbers: IntArray): IntArray {
        val answer: IntArray = IntArray(numbers.size) { -1 }
        val stack = mutableListOf<Int>()

        var index = 0
        numbers.forEachIndexed { i, number -> // number가 뒷큰수가 되는 경우, answerIndex < i 여야한다.
            while (index <= i) {
                val stackTop = if (stack.isNotEmpty()) stack[stack.lastIndex] else -1

                if (stack.isEmpty() || number <= numbers[stackTop]) {
                    stack.add(index++)
                } else {
                    answer[stackTop] = number
                    stack.removeLast()
                }
            }
        }

        return answer
    }
}