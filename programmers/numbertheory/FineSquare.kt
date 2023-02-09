class FineSquare {
    fun solution(w: Int, h: Int): Long {
        val n: Long = w.toLong() * h
        val gcd = getGcd(maxOf(w, h), minOf(w, h))

        return n - (w + h - gcd)
    }

    private fun getGcd(x: Int, y: Int): Int {
        if (y == 0) return x
        return getGcd(y, x % y)
    }
}