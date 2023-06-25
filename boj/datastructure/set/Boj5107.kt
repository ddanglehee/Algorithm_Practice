class Boj5107 {

    fun main() = with(System.`in`.bufferedReader()) {

        val sb = StringBuilder()
        var t = 1
        while (true) {
            val n = readLine().toInt()

            if (n == 0) break

            var answer = 0
            val set = mutableSetOf<String>()
            repeat(n) {
                val (s1, s2) = readLine().split(" ")

                if (set.contains(s1) && set.contains(s2)) answer++
                set.add(s1); set.add(s2)
            }

            sb.append(t).append(" ").append(answer).append("\n")
            t++
        }



        print(sb)
    }
}