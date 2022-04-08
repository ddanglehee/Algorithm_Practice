class TopologySort {

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (v, e) = br.readLine().split(" ").map { it.toInt() }
        val indegreeTable = Array(v + 1) { 0 }
        val graph = List(v + 1) { mutableListOf<Int>() }
        repeat(e) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            graph[a].add(b)
            indegreeTable[b]++
        }
        val result = mutableListOf<Int>()
        val queue = mutableListOf<Int>()

        for (i in 1..v) {
            if (indegreeTable[i] == 0) {
                queue.add(i)
            }
        }

        while (queue.isNotEmpty()) {
            val currentNode = queue.removeAt(0)
            result.add(currentNode)

            graph[currentNode].forEach { adjacentNode ->
                indegreeTable[adjacentNode]--
                if (indegreeTable[adjacentNode] == 0) {
                    queue.add(adjacentNode)
                }
            }
        }

        result.forEach {
            print("$it ")
        }
    }
}