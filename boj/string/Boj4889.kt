class Boj4889 {
    fun main() = with(System.`in`.bufferedReader()) {
        var t = 1
        val sb = StringBuilder()
        val stack = mutableListOf<Char>()
        while(true) {
            val input = readLine()
            if (input[0] == '-') {
                break
            }

            stack.clear()
            var answer = 0
            for (c in input) {
                when (c) {
                    '{' -> {
                        stack.add('{')
                    }
                    '}' -> {
                        if (stack.isEmpty()) {
                            answer++
                            stack.add('{')
                        } else {
                            stack.removeLast()
                        }
                    }
                }
            }
            answer += (stack.size / 2)
            sb.append(t++).append(". ").append(answer).append("\n")
        }

        print(sb)
    }
}