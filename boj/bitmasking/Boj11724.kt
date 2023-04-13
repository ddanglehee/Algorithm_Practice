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

    // 새로운 풀이
    fun main2() = with(System.`in`.bufferedReader()) {
        val sb = StringBuilder()

        val m = readLine().toInt()
        var s = 0

        repeat(m) {
            val input = readLine()

            if (input == "all") {
                s = (1 shl 20) - 1
            } else if (input == "empty") {
                s = 0
            } else {
                val (command, nString) = input.split(" ")
                val number = nString.toInt() - 1

                when (command) {
                    "add" -> {
                        s = s or (1 shl number)
                    }
                    "remove" -> {
                        s = s and (1 shl number).inv()
                    }
                    "check" -> {
                        if (1 <= (s and (1 shl number))) {
                            sb.append(1).append("\n")
                        } else {
                            sb.append(0).append("\n")
                        }
                    }
                    else -> {
                        s = s xor (1 shl number)
                    }
                }
            }
        }

        print(sb)
    }
}