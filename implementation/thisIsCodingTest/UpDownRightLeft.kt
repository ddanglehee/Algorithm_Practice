class UpDownRightLeft {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val input = br.readLine().split(" ")

        var curRow = 1
        var curColumn = 1

        for (direction in input) {
            when (direction) {
                "L" -> {
                    if (curColumn != 1) {
                        curColumn--
                    }
                }
                "R" -> {
                    if (curColumn != n) {
                        curColumn++
                    }
                }
                "U" -> {
                    if (curRow != 1) {
                        curRow--
                    }
                }
                "D" -> {
                    if (curRow != n) {
                        curRow++
                    }
                }
            }
        }

        println("$curRow $curColumn")
    }
}