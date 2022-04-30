class FindCityOfSpecificDistance {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m, k, x) = br.readLine().split(" ").map { it.toInt() }
        val graph = Array(n + 1) { mutableListOf<Int>() }
        val distance = Array(n + 1) { 987654321 }
        repeat(m) {
            val (a, b) = br.readLine().split(" ").map { it.toInt() }
            graph[a].add(b)
        }

        distance[x] = 0
        dfs(distance, graph, x, 0)

        var check = false
        distance.forEachIndexed { index, d ->
            if (d == k) {
                println(index)
                check = true
            }
        }

        if (!check) {
            println(-1)
        }
    }

    private fun dfs(distance: Array<Int>, graph: Array<MutableList<Int>>, currentCity: Int, dist: Int) {
        for (city in graph[currentCity]) {
            if (distance[currentCity] + 1 < distance[city]) {
                distance[city] =  distance[currentCity] + 1
                dfs(distance, graph, city, distance[currentCity] + 1)
            }
        }
    }
}