class Babbling {

    fun solution(babbling: Array<String>): Int {
        var answer: Int = 0

        babbling.forEach {
            var word = it
            if (!(word.contains("ayaaya") || word.contains("yeye") || word.contains("woowoo") || word.contains("mama"))) {
                word = word.replace("aya", " ")
                word = word.replace("ye", " ")
                word = word.replace("woo", " ")
                word = word.replace("ma", " ")
                word = word.replace(" ", "")

                if (word.isEmpty()) answer++
            }
        }

        return answer
    }
}