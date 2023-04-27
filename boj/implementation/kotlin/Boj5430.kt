class Boj5430 {

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()

        var command: String
        var count: Int
        var list: List<String>
        var startIdx: Int
        var lastIdx: Int
        var reversed: Boolean
        var error: Boolean

        repeat(t) {
            command = readLine()
            count = readLine().toInt()
            list = readLine().removeSurrounding("[", "]").split(",")
            startIdx = 0
            lastIdx = if (count != 0) list.lastIndex else -1
            reversed = false
            error = false

            for (c in command) {
                when(c) {
                    'R' -> {
                        val tmp = startIdx
                        startIdx = lastIdx
                        lastIdx = tmp
                        reversed = !reversed
                    }
                    'D' -> {
                        if ((!reversed && lastIdx < startIdx) || (reversed && startIdx < lastIdx)) {
                            error = true
                            break
                        }
                        if (!reversed) startIdx++ else startIdx--
                    }
                }
            }

            if (error) {
                sb.append("error\n")
            } else {
                sb.append("[")

                if (reversed) {
                    for (i in startIdx downTo lastIdx) {
                        sb.append(list[i])
                        if (i != lastIdx) {
                            sb.append(",")
                        }
                    }
                } else {
                    for (i in startIdx..lastIdx) {
                        sb.append(list[i])
                        if (i != lastIdx) {
                            sb.append(",")
                        }
                    }
                }

                sb.append("]\n")
            }
        }

        print(sb)
    }
}