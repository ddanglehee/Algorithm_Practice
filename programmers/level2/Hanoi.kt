class Hanoi {

    val moveList = ArrayList<IntArray>()
    fun solution(n: Int): Array<IntArray> {
        hanoi(n, 1, 3, 2)
        return moveList.toTypedArray()
    }

    fun hanoi(n: Int, from: Int, to: Int, via: Int) {
        if (n == 1) {
            move(n, from, to)
        } else {
            hanoi(n - 1, from, via, to)
            move(n, from, to)
            hanoi(n - 1, via, to, from)
        }
    }

    fun move(n: Int, from: Int, to: Int) {
        moveList.add(intArrayOf(from, to))
    }
}