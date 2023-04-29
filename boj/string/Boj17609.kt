class Boj17609 {

    fun main() = with(System.`in`.bufferedReader()) {
        val t = readLine().toInt()
        val sb = StringBuilder()

        repeat(t) {
            val input = readLine()
            var start = 0; var end = input.lastIndex
            var answer = 0

            while (start < end) {
                if (input[start] != input[end]) {
                    answer++
                    if (!isPalindrome(input.substring(start, end)) && !isPalindrome(input.substring(start + 1, end + 1))) answer++
                    break
                }

                start++
                end--
            }

            sb.append(answer).append("\n")
        }

        print(sb)
    }

    fun isPalindrome(str: String): Boolean {
        var start = 0; var end = str.lastIndex

        while (start < end) {
            if (str[start] == str[end]) {
                start++
                end--
            } else {
                return false
            }
        }

        return true
    }
}