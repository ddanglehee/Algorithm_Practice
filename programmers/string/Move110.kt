class Move110 {
    fun solution(s: Array<String>): Array<String> {
        val answer = Array<String>(s.size) { "" }

        s.forEachIndexed { idx, str ->
            answer[idx] = operate(str)
        }

        return answer
    }

    fun operate(str: String): String {
        val tmp = mutableListOf<Char>()
        var count = 0

        for (c in str) {
            if (2 <= tmp.size) {
                val second = tmp.removeLast()
                val first = tmp.removeLast()

                if (first == '1' && second == '1' && c == '0') {
                    count++
                } else {
                    tmp.add(first)
                    tmp.add(second)
                    tmp.add(c)
                }
            } else {
                tmp.add(c)
            }
        }

        var newString = tmp.joinToString("")
        var index11 = newString.length
        var index0 = -1
        for (index in 0 until newString.length) {
            if (newString[index] == '0') {
                index0 = maxOf(index0, index)
            } else if (index < newString.length - 1 && newString.substring(index, index + 2) == "11") {
                index11 = minOf(index11, index)
            }
        }

        val sb = StringBuilder()
        if (index11 < index0) {
            sb.append(newString.substring(0, index11))
            for (i in 0 until count) {
                sb.append("110")
            }
            sb.append(newString.substring(index11))
        } else {
            sb.append(newString.substring(0, index0 + 1))
            for (i in 0 until count) {
                sb.append("110")
            }
            sb.append(newString.substring(index0 + 1))
        }

        return sb.toString()
    }
}