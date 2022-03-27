class PrintStudentInDescendingOrder {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val studentList = mutableListOf<Pair<String, Int>>()

        repeat(n) {
            val input = br.readLine().split(" ")
            studentList.add(input[0] to input[1].toInt())
        }

        studentList.sortBy { it.second }

        studentList.forEach {
            print("${it.first} ")
        }
    }
}