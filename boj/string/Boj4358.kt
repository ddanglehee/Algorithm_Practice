class Boj4358 {

    fun main() = with (System.`in`.bufferedReader()) {
        val sb = StringBuilder()

        val map = mutableMapOf<String, Int>()
        var totalTrees = 0

        while (true) {
            val tree = readLine() ?: break
            if (tree == "") break

            map[tree] = map.getOrDefault(tree, 0) + 1
            totalTrees++
        }

        val treeList = map.keys.toMutableList().sorted()
        for (tree in treeList) {
            val count = map[tree]!!

            sb.append(tree).append(" ").append(String.format("%.4f", (count * 100).toDouble() / totalTrees.toDouble())).append("\n")
        }

        println(sb)
    }
}