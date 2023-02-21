class PasswordOfTwo {

    fun solution(s: String, skip: String, index: Int): String {
        var answer: String = ""

        val skipArray = skip.toCharArray()

        s.forEach { c ->
            answer += c.plus(index, skipArray)
        }

        return answer
    }

    private fun Char.plus(index: Int, skipArray: CharArray): Char {
        var count = 1
        var currentChar = this

        while (count <= index) {

            if ('z' < currentChar + 1) {
                currentChar = 'a'
            } else {
                currentChar += 1
            }
            if (!skipArray.contains(currentChar)) count++
        }

        return currentChar
    }
}