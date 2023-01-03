class Boj5639 {

    val sb = StringBuilder()

    fun main() = with(System.`in`.bufferedReader()) {
        val tree = mutableListOf<Node>()

        val root = readLine().toInt()
        tree.add(Node(root))
        while (true) {
            try {
                val n = readLine().toInt()
                val newNode = Node(n)
                tree.add(Node(root))
                addToTree(tree, newNode)
            } catch (e: Exception) {
                break
            }
        }
        postOrder(tree, tree[0])
        print(sb)
    }

    fun addToTree(tree: MutableList<Node>, newNode: Node) {
        var currentNode = tree[0]

        while (true) {
            if (currentNode.number < newNode.number) {
                if (currentNode.right != null) {
                    currentNode = currentNode.right!!
                } else {
                    currentNode.right = newNode
                    break
                }
            } else {
                if (currentNode.left != null) {
                    currentNode = currentNode.left!!
                } else {
                    currentNode.left = newNode
                    break
                }
            }
        }
    }

    fun postOrder(tree: MutableList<Node>, currentNode: Node?) {
        if (currentNode == null) return
        postOrder(tree, currentNode.left)
        postOrder(tree, currentNode.right)
        sb.append(currentNode.number).append("\n")
    }

    data class Node(val number: Int) {
        var right: Node? = null
        var left: Node? = null
    }
}