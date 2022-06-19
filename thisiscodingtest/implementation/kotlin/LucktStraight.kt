class LuckyStraight {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val number = br.readLine()
        val left = number.substring(0 until number.length / 2 )
        val right = number.substring(number.length / 2 until number.length)

        var sum = 0
        left.forEach {
            sum += it.digitToInt()
        }

        right.forEach {
            sum -= it.digitToInt()
        }

        if (sum == 0) {
            println("LUCKY")
        } else {
            println("READY")
        }
    }
}