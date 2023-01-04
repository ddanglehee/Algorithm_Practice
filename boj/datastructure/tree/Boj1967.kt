class Boj1967 {

    var diameter = 0

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val tree = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        val isLeafNode = BooleanArray(n + 1) { true }

        var rootChildrenCount = 0
        repeat(n - 1) {
            val (parent, child, weight) = readLine().split(" ").map { it.toInt() }
            if (parent == 1) {
                rootChildrenCount++
            }
            isLeafNode[parent] = false

            tree[parent].add(child to weight)
            tree[child].add(parent to weight)
        }

        if (rootChildrenCount == 1) {
            isLeafNode[1] = true
        }
        for (start in 1..n) {
            if (!isLeafNode[start]) continue

            calculateDiameter(tree, -1, start, 0, isLeafNode)
        }

        println(diameter)
    }

    fun calculateDiameter(tree: Array<MutableList<Pair<Int, Int>>>, pre: Int,  current: Int, tmpDiameter: Int, isLeafNode: BooleanArray) {
        if (pre != -1 && isLeafNode[current]) {
            diameter = maxOf(diameter, tmpDiameter)
            return
        }

        for (next in tree[current]) {
            if (pre == next.first) continue

            calculateDiameter(tree, current, next.first, tmpDiameter + next.second, isLeafNode)
        }
    }
}