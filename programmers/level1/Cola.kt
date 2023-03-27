class Cola {
    fun solution(a: Int, b: Int, n: Int): Int {
        var answer: Int = 0
        var remain = n

        while (a <= remain) {
            val ret = (remain / a) * b
            answer += ret
            remain = ret + (remain % a)
        }

        return answer
    }
}