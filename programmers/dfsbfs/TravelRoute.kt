class TravelRoute {


    private val tmp = mutableListOf<String>()
    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        val route = hashMapOf<String, MutableList<String>>()
        val visited = hashMapOf<String, Int>()

        tickets.forEach { ticket ->
            val from = ticket[0]
            val to = ticket[1]

            if (route[from] == null) {
                route[from] = mutableListOf(to)
            } else {
                route[from]!!.add(to)
            }
            visited[to] = (visited[to] ?: 0) + 1
        }

        route.keys.forEach { key ->
            route[key]!!.sort()
        }

        visited["ICN"] = (visited["ICN"] ?: 0) + 1
        dfs("ICN", route, visited, tickets.size + 1)

        return answer.toTypedArray()
    }

    private fun dfs(current: String, route: HashMap<String, MutableList<String>>, visited: HashMap<String, Int>, size: Int): Boolean {
        visited[current] = (visited[current] ?: 0) - 1
        tmp.add(current)

        if (tmp.size == size) {
            answer.addAll(tmp)
            return true
        }


        route[current]?.forEach { next ->
            if (visited[next] != 0) {
                if(dfs(next, route, visited, size)) {
                    return true
                }
            }
        }


        visited[current] = (visited[current] ?: 0) + 1
        tmp.removeLast()
        return false
    }
}