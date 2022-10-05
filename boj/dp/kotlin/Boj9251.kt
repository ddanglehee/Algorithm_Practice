class Boj9251 {

    fun main() = with (System.`in`.bufferedReader()) {
        val s1 = readLine()
        val s2 = readLine()
        val s1Count = s1.length
        val s2Count = s2.length

        val lcs = Array(s1Count + 1) { IntArray(s2Count + 1) }

        for (i in 1..s1Count) {
            for (j in 1..s2Count) {
                if (s1[i - 1] == s2[j - 1]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1
                } else {
                    lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1])
                }
            }
        }

        println(lcs[s1Count][s2Count])
    }
}