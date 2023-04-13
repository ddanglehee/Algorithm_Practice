class Boj11724 {


    fun main() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()

        val m = readLine().toInt()
        val S = BooleanArray(21)

        repeat(m) {
            val input = readLine()

            if (input == "all") {
                for (i in 1..20) {
                    S[i] = true
                }
            } else if (input == "empty") {
                for (i in 1..20) {
                    S[i] = false
                }
            } else {
                val (command, number) = input.split(" ")

                when (command) {
                    "add" -> {
                        S[number.toInt()] = true
                    }
                    "remove" -> {
                        S[number.toInt()] = false
                    }
                    "check" -> {
                        if (S[number.toInt()]) {
                            sb.append(1).append("\n")
                        } else {
                            sb.append(0).append("\n")
                        }
                    }
                    else -> {
                        S[number.toInt()] = !S[number.toInt()]
                    }
                }
            }
        }

        print(sb)
    }
}