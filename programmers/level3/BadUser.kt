class Solution {

    var answerSet = mutableSetOf<String>()

    fun solution(user_id: Array<String>, banned_id: Array<String>): Int {

        val bannedMatch = Array(banned_id.size) {
            mutableListOf<Int>()
        }
        val isSelected = BooleanArray(user_id.size)

        banned_id.forEachIndexed { i, id ->
            val regex = id.replace("*", ".").toRegex()

            user_id.forEachIndexed { j, uid ->
                val result = regex.matchEntire(uid)
                if (result != null) {
                    bannedMatch[i].add(j)
                }
            }
        }

        findCombination(0, banned_id.size, bannedMatch, isSelected)

        return answerSet.size
    }

    fun findCombination(curIndex: Int, bannedSize: Int, bannedMatch: Array<MutableList<Int>>, isSelected: BooleanArray) {
        if (bannedSize == curIndex) {
            answerSet.add(isSelected.contentToString())
            return
        }

        bannedMatch[curIndex].forEach { i ->
            if (isSelected[i]) return@forEach

            isSelected[i] = true
            findCombination(curIndex + 1, bannedSize, bannedMatch, isSelected)
            isSelected[i] = false
        }
    }
}