class FromTopToBottom {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()

        val list = mutableListOf<Int>()
        repeat(n) {
            list.add(br.readLine().toInt())
        }

        list.sortDescending()
        println(list)
    }
}