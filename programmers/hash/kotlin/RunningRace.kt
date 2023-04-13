class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val rankMap = mutableMapOf<String, Int>()

        for (i in players.indices) {
            rankMap[players[i]] = i
        }

        for (name in callings) {
            val beforeRank = rankMap[name]!!
            rankMap[name] = beforeRank - 1;
            rankMap[players[beforeRank - 1]] = beforeRank;
            players[beforeRank] = players[beforeRank - 1];
            players[beforeRank - 1] = name;
        }

        return players
    }
}