class SwapElementsInTwoArray {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, k) = br.readLine().split(" ").map { it.toInt() }
        val a = br.readLine().split(" ").map { it.toInt() }.toMutableList()
        val b = br.readLine().split(" ").map { it.toInt() }.toMutableList()

        a.sort()
        b.sortDescending()

        for (i in 0 until k) {
            if (b[i] <= a[i]) break
            a[i] = b[i].also { b[i] = a[i] }
        }

        println(a.sum())
    }
}