class LuckyStraight {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val number = br.readLine()
        val left = number.substring(0 until number.length / 2 )
        val right = number.substring(number.length / 2 until number.length)

        var leftSum = 0
        left.forEach {
            leftSum += it.digitToInt()
        }

        var rightSum = 0
        right.forEach {
            rightSum += it.digitToInt()
        }

        if (rightSum == leftSum) {
            println("LUCKY")
        } else {
            println("READY")
        }
    }
}