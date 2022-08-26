class TravelRoute {

    private val tmp = mutableListOf<String>()
    private val answer = mutableListOf<String>()

    fun solution(tickets: Array<Array<String>>): Array<String> {
        val route = hashMapOf<String, MutableList<Ticket>>()

        tickets.forEach { ticket ->
            val from = ticket[0]
            val to = ticket[1]

            if (route[from] == null) {
                route[from] = mutableListOf(Ticket(to))
            } else {
                route[from]!!.add(Ticket(to))
            }
        }

        route.keys.forEach { key ->
            route[key]!!.sortBy { it.to }
        }

        dfs("ICN", route, tickets.size + 1)

        return answer.toTypedArray()
    }

    private fun dfs(current: String, route: HashMap<String, MutableList<Ticket>>, size: Int): Boolean {
        tmp.add(current)

        if (tmp.size == size) {
            answer.addAll(tmp)
            return true
        }


        route[current]?.forEach { ticket ->
            if (!ticket.isUsed) {
                ticket.isUsed = true
                if(dfs(ticket.to, route, size)) {
                    return true
                }
                ticket.isUsed = false
            }
        }

        tmp.removeLast()
        return false
    }

    data class Ticket(val to: String, var isUsed: Boolean = false) {
    }
}