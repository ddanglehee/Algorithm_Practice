class AdventurersGuild {
    fun solution() {
        val br = System.`in`.bufferedReader()
        val n = br.readLine().toInt()
        val fears = br.readLine().split(" ").map { it.toInt() }.sorted()

        var result = 0
        var currentTeamCount = 0 // 현재 팀원 수
        for (fear in fears) {
            currentTeamCount++ // 현재 팀에 해당 공포도를 가진 모험가 추가

            if (currentTeamCount == fear) { // 현재 팀 최소 인원이 다 찼으면 팀 결성
                result++
                currentTeamCount = 0
            }
        }

        println(result)
    }
}