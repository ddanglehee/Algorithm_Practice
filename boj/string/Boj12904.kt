class Boj12904 {

    fun main() = with(System.`in`.bufferedReader()) {
        val S = readLine()
        val T = readLine()

        val TList = T.toMutableList()
        repeat(T.length - S.length) {
            val last = TList.removeLast()
            if (last == 'B') {
                TList.reverse()
            }
        }

        if (TList.joinToString("") == S) {
            print(1)
        } else {
            print(0)
        }
    }
}