class Boj1967 {

    var diameter = 0
    var node = 1

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()

        val tree = Array(n + 1) { mutableListOf<Pair<Int, Int>>() }
        repeat(n - 1) {
            val (parent, child, weight) = readLine().split(" ").map { it.toInt() }

            tree[parent].add(child to weight)
            tree[child].add(parent to weight)
        }

        findFarthestNode(tree, -1, 1, 0)
        findFarthestNode(tree, -1, node, 0)

        println(diameter)
    }

    fun findFarthestNode(tree: Array<MutableList<Pair<Int, Int>>>, pre: Int, current: Int, tmpDiameter: Int) {
        if (diameter < tmpDiameter) {
            diameter = tmpDiameter
            node = current
        }

        for (next in tree[current]) {
            if (next.first != pre) {
                findFarthestNode(tree, current, next.first, tmpDiameter + next.second)
            }
        }
    }
}