class DictCollection {

    private var answer = 0
    private val alphabets = arrayOf('A', 'E', 'I', 'O', 'U')

    fun solution(word: String): Int {
        find("", word)
        return answer
    }

    private fun find(currentWord: String, word:String): Boolean {
        if (currentWord == word) {
            return true
        }

        for (alphabet in alphabets) {
            val nextWord = currentWord + alphabet
            if (5 < nextWord.length) break

            answer++
            val isFound = find(currentWord + alphabet, word)
            if (isFound) {
                return true
            }
        }

        return false
    }
}