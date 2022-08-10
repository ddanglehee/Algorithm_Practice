class KthNumber {
    fun solution(array: IntArray, commands: Array<IntArray>): IntArray {
        var answer = mutableListOf<Int>()

        commands.forEach { command ->
            val subArray = array.copyOfRange(command[0] - 1, command[1])
            subArray.sort()
            answer.add(subArray[command[2] - 1])
        }

        return answer.toIntArray()
    }
}