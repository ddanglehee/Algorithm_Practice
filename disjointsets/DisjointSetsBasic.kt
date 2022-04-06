class DisjointSetsBasic {
    private lateinit var parentTable: Array<Int>

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        parentTable = Array(v + 1) { it }

        repeat(e) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            unionParent(a, b)
        }

        print("각 원소가 속한 집합 : ")
        for (i in 1..v) {
            print("${findParent(i)} ")
        }
        println()
        print("부모 테이블 : ")
        for(i in 1..v) {
            print("${parentTable[i]} ")
        }
    }

    private fun unionParent(a: Int, b: Int) {
        val aParent = findParent(a)
        val bParent = findParent(b)

        if (aParent < bParent)  {
            parentTable[bParent] = aParent
        } else if (aParent > bParent) {
            parentTable[aParent] = bParent
        }
    }


    private fun findParent(a: Int): Int {
        return if (parentTable[a] == a) {
            a
        } else {
            findParent(parentTable[a])
        }
    }
}