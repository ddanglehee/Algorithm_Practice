class Solution {
    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        var answer = Array(2) {
            IntArray(nodeinfo.size)
        }

        val map = mutableMapOf<Pair<Int, Int>, Int>()
        nodeinfo.forEachIndexed { i, point ->
            map[Pair(point[0], point[1])] = i + 1
        }

        nodeinfo.sortWith(compareByDescending<IntArray> { it[1] }.thenBy { it[0] })
        val rootX = nodeinfo[0][0]; val rootY = nodeinfo[0][1]
        val root = Node(map[Pair(rootX, rootY)]!!, rootX, rootY)

        for (i in 1 until nodeinfo.size) {
            val x = nodeinfo[i][0]; val y = nodeinfo[i][1]
            val newNode = Node(map[Pair(x, y)]!!, x, y)

            var curNode: Node? = root
            // 자기 위치에 newNode 삽입
            while (true) {
                if (curNode == null) break

                if (x < curNode.x) {
                    if (curNode.left == null) {
                        curNode.left = newNode
                        break
                    } else {
                        curNode = curNode.left
                    }
                } else {
                    if (curNode.right == null) {
                        curNode.right = newNode
                        break
                    } else {
                        curNode = curNode.right
                    }
                }
            }
        }

        val order = mutableListOf<Int>()
        preorder(root, order)
        answer[0] = order.toIntArray()
        order.clear()
        postorder(root, order)
        answer[1] = order.toIntArray()

        return answer
    }

    fun preorder(node: Node?, order: MutableList<Int>) {
        if (node == null) return

        order.add(node.number)
        preorder(node.left, order)
        preorder(node.right, order)
    }

    fun postorder(node: Node?, order: MutableList<Int>) {
        if (node == null) return

        postorder(node.left, order)
        postorder(node.right, order)
        order.add(node.number)
    }
}

data class Node(val number: Int, val x: Int, val y: Int) {
    var left: Node? = null
    var right: Node? = null
}