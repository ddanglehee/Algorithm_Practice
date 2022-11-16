class Boj2002 {

    fun main() = with(System.`in`.bufferedReader()) {
        val n =  readLine().toInt()

        val inMap = mutableMapOf<String, Int>()
        for (i in 1..n) {
            val carNumber = readLine()
            inMap[carNumber] = i
        }

        val outList = mutableListOf<String>("")
        for (i in 1..n) {
            val carNumber = readLine()
            outList.add(carNumber)
        }

        var answer = 0
        for (outSequence in 1..n) {
            val carNumber = outList[outSequence]

            for (pSequence in outSequence + 1..n) {
                if (inMap[outList[pSequence]]!! < inMap[carNumber]!!) {
                    answer++
                    break
                }
            }
        }

        println(answer)
    }
}