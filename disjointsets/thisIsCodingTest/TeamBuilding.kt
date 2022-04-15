class TeamBuilding {

    private lateinit var team: Array<Int>

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        team = Array(n + 1) { it }
        repeat(m) {
            val (operator, a, b) = br.readLine().split(" ").map { it.toInt() }
            if (operator == 0) {
                unionTeam(a, b)
            } else if (operator == 1) {
                val aTeam = findTeam(a)
                val bTeam = findTeam(b)

                if (aTeam == bTeam) {
                    println("YES")
                } else {
                    println("NO")
                }
            }
        }
    }

    private fun unionTeam(a: Int, b: Int) {
        val aTeam = findTeam(a)
        val bTeam = findTeam(b)

        if (aTeam < bTeam) {
            team[bTeam] = aTeam
        } else if (aTeam > bTeam) {
            team[aTeam] = bTeam
        }
    }

    private fun findTeam(x: Int): Int {
        if (team[x] != x) {
            team[x] = findTeam(team[x])
        }
        return team[x]
    }
}