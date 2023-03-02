class SeesawPair {
    fun solution(weights: IntArray): Long {
        var answer: Long = 0
        weights.sort() // weight를 오름차순으로 정렬해놓고 시작
        val torqueCountOf = IntArray(4001) // index : (탑승한 사람의 무게) * (시소 축과 좌석 간의 거리)
        val weightCountOf = IntArray(1001) // index : 사람의 몸무게 value : 몸무게 index를 가진 사람의 수

        for (currentWeight in weights) {
            val rideWeights = intArrayOf(currentWeight * 2, currentWeight * 3, currentWeight * 4)

            rideWeights.forEach { weight ->
                answer += torqueCountOf[weight]++
            }
            if (weightCountOf[currentWeight] != 0) {
                answer -= weightCountOf[currentWeight] * 2
            }

            weightCountOf[currentWeight]++
        }

        return answer
    }
}