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

    // 더 효율이 좋고 간단한 풀이
    fun solution2(s: String, skip: String, index: Int): String {
        var answer: String = ""
        val alphabet = ('a'..'z').filter { it !in skip }

        s.forEach { c ->
            val newIndex = (alphabet.indexOf(c) + index) % alphabet.size
            answer += alphabet[newIndex]
        }

        return answer
    }
}