class Until1 {
    fun solution() {
        var (n, k) = readLine()!!.split(" ").map{ it.toInt() }

        var result = 0

        while (n != 1) {
            if (n % k == 0) {
                n /= k
            } else {
                n -= 1
            }
            result++
        }

        println(result)
    }
}