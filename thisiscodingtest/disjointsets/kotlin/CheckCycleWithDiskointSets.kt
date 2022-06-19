class CheckCycleWithDisjointSets {

    private lateinit var parentTable: Array<Int>

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        parentTable = Array(v + 1) { it }
        for (i in 1..e) {
            val (a, b) = br.readLine().split(" ").map{ it.toInt() }
            val isUnionSuccess = unionParent(a, b)
            if (!isUnionSuccess) break
        }
    }

    // 사이클이 존재한다면 union 실패
    private fun unionParent(a: Int, b: Int): Boolean {
        val aParent = findParent(a)
        val bParent = findParent(b)

        if (aParent < bParent) {
            parentTable[bParent] = aParent
        } else if (bParent < aParent) {
            parentTable[aParent] = bParent
        } else {
            return false
        }
        return true
    }

    private fun findParent(a: Int): Int {
        if (parentTable[a] != a) {
            parentTable[a] = findParent(parentTable[a])
        }
        return parentTable[a]
    }
}