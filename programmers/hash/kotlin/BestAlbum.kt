class BestAlbum {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = arrayListOf<Int>()
        val genresMap = mutableMapOf<String, ArrayList<Music>>() // key: 장르, value: 해당 장르 노래 리스트
        val genresPlaysCountMap = mutableMapOf<String, Int>()

        for (musicNumber in genres.indices) {
            val genre = genres[musicNumber]
            val playsCount = plays[musicNumber]

            if (genre !in genresMap.keys) {
                genresMap[genre] = arrayListOf(Music(musicNumber, playsCount))
                genresPlaysCountMap[genre] = playsCount
            } else {
                genresMap[genre]!!.add(Music(musicNumber, playsCount))
                genresPlaysCountMap[genre] = genresPlaysCountMap[genre]!! + playsCount
            }
        }

        // 속한 노래가 많이 재생된 장르순으로 정렬
        val genreList = genresPlaysCountMap.toList().sortedByDescending { it.second }.map { it.first }
        genreList.forEach { genre ->
            // plays가 많이 재생된 노래 순으로 정렬하고, 재생횟수가 같은 노래 중에는 고유번호가 낮은 노래 순으로 정렬
            val musicList = genresMap[genre]!!.sortedWith(compareByDescending<Music> { it.plays }.thenBy { it.num })
            answer.add(musicList[0].num)
            if (1 < musicList.size) answer.add(musicList[1].num)
        }

        return answer.toIntArray()
    }

    data class Music(val num: Int, val plays: Int)
}