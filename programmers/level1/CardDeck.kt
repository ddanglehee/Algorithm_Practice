class CardDeck {
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var answer: String = "Yes"

        var i = 0; var j = 0
        goal.forEach { word ->
            if (i < cards1.size && cards1[i] == word) {
                i++
            } else if (j < cards2.size && cards2[j] == word){
                j++
            } else {
                answer = "No"
                return@forEach
            }
        }

        return answer
    }
}