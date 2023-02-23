class RoughKeyboard {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        var answer: IntArray = IntArray(targets.size)
        val alphabetMap = mutableMapOf<Char, Int>()

        keymap.forEach { keys ->
            keys.forEachIndexed { i, alphabet ->
                alphabetMap[alphabet] = minOf(alphabetMap[alphabet] ?: 100, i + 1)
            }
        }

        targets.forEachIndexed { i, target ->
            var count = 0
            target.forEach { alphabet ->
                if (alphabetMap[alphabet] == null) {
                    count = -1
                    return@forEach
                } else {
                    count += alphabetMap[alphabet]!!
                }
            }
            answer[i] = count
        }


        return answer
    }
}