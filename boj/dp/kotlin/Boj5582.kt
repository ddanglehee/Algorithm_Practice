class Boj5582 {

    fun main() = with (System.`in`.bufferedReader()) {
        val s1 = readLine()
        val s2 = readLine()
        val s1Length = s1.length
        val s2Length = s2.length
        var result = 0

        val lcs = Array(s1Length + 1) { IntArray(s2Length + 1) }

        for (i in 1..s1Length) {
            for (j in 1..s2Length) {
                if (s1[i - 1] == s2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1
                    result = maxOf(result, lcs[i][j])
                }
            }
        }

        println(result)
    }
}