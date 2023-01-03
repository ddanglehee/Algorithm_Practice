class Boj1068 {

    var count = 0

    fun main() = with(System.`in`.bufferedReader()) {
        val n = readLine().toInt()
        val input = readLine().split(" ").map { it.toInt() }

        val tree = Array(n) { Node() }
        var rootNode = -1
        for (i in 0 until n) {
            val parent = input[i]
            if (parent == -1) {
                rootNode = i
                continue
            }

            tree[parent].child.add(i)
            tree[i].parent = parent
        }

        val removeNode = readLine().toInt()
        if (removeNode == rootNode) {
            println(0)
        } else {
            tree[tree[removeNode].parent].child.remove(removeNode)
            countLeafNode(rootNode, tree)
            println(count)
        }
    }

    fun countLeafNode(currentNode: Int, tree: Array<Node>) {
        if (tree[currentNode].child.isEmpty()) {
            count++
        } else {
            for (child in tree[currentNode].child) {
                countLeafNode(child, tree)
            }
        }
    }

    data class Node(var parent: Int = -1, val child: MutableList<Int> = mutableListOf())
}