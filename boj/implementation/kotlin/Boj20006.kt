class Boj20006 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (p, m) = readLine().split(" ").map { it.toInt() }

        val rooms = mutableListOf<MutableList<Pair<Int, String>>>()
        repeat(p) {
            val (l, n) = readLine().split(" ")

            if (rooms.isEmpty()) {
                rooms.add(mutableListOf(Pair(l.toInt(), n)))
            } else {
                var find = false

                for (room in rooms) {
                    if (room.size == m || l.toInt() !in room[0].first - 10..room[0].first + 10) continue

                    room.add(Pair(l.toInt(), n))
                    find = true
                    break
                }

                if (!find) rooms.add(mutableListOf(Pair(l.toInt(), n)))
            }
        }

        val sb = StringBuilder()
        for (room in rooms) {
            if (room.size == m) {
                sb.append("Started!")
            } else {
                sb.append("Waiting!")
            }
            sb.append("\n")
            room.sortBy { it.second }
            for (player in room) {
                sb.append(player.first).append(" ").append(player.second).append("\n")
            }
        }

        print(sb)
    }
}