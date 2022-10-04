class Boj1013 {

    fun main() = with (System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val sb = StringBuilder()

        repeat(n) {
            val input = readLine()
            val regex = Regex("(100+1+|01)+")
            val result = regex.matchEntire(input)

            if (result != null) {
                sb.append("YES\n")
            } else {
                sb.append("NO\n")
            }
        }

        println(sb)
    }
}