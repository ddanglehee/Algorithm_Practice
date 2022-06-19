class UpDownRightLeft {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val input = br.readLine().split(" ").map{ it.single() } // CharSequence.single()을 통해 input을 Char리스트로 만듦

        val dx = listOf(0, 0, -1, 1)
        val dy = listOf(-1, 1, 0, 0)
        val directions = charArrayOf('L', 'R', 'U', 'D')

        var curX = 1
        var curY = 1

        for (direction in input) {
            val directionIndex = directions.indexOf(direction)
            val tmpX= curX + dx[directionIndex]
            val tmpY = curY + dy[directionIndex]

            if (tmpX in 1..n && tmpY in 1..n) {
                curX = tmpX
                curY = tmpY
            }
        }

        println("$curX $curY")
    }
}