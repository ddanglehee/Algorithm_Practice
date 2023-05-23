class LongestPalindrome {

    fun solution(s: String): Int {
        var answer = 1

        for (i in 0 until s.lastIndex) {
            var tmp = 1
            var start = i - 1; var end = i + 1

            while (start in 0..s.lastIndex && end in 0..s.lastIndex) {
                if (s[start] != s[end]) break
                tmp += 2
                start--; end++
            }

            answer = maxOf(answer, tmp)

            if (s[i] == s[i + 1]) {
                tmp = 2
                start = i - 1; end = i + 2

                while (start in 0..s.lastIndex && end in 0..s.lastIndex) {
                    if (s[start] != s[end]) break
                    tmp += 2
                    start--; end++
                }
                answer = maxOf(answer, tmp)
            }
        }

        return answer
    }
}