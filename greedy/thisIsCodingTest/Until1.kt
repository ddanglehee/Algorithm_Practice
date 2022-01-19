class Until1 {
    fun solution() {
        var (n, k) = readLine()!!.split(" ").map{ it.toInt() }

        var result = 0

        while (n != 1) {
            if (k <= n) {
                if (n % k == 0) {
                    n /= k
                } else {
                    n -= 1
                }
                result++
            } else {
                // n이 k보다 작아질 경우 1씩 n-1번 빼는 시간을 아낄 수 있다.
                result += n - 1
                n = 1
            }
        }

        println(result)
    }
}