class BinarySearchBasic {
    fun binarySearchRecursive(list: List<Int>, target: Int, start: Int, end: Int): Int {
        if (end < start) return -1

        val mid = (start + end) / 2

        return when {
            target < list[mid] -> binarySearchRecursive(list, target, start, mid - 1)
            target == list[mid] -> mid
            else -> binarySearchRecursive(list, target, mid + 1, end)
        }
    }

    fun binarySearchIterative(list: List<Int>, target: Int): Int {
        var start = 0
        var end = list.lastIndex

        while (start <= end) {
            val mid = (start + end) / 2

            when {
                target < list[mid] -> end = mid - 1
                target == list[mid] -> return mid
                else -> start = mid + 1
            }
        }

        return -1
    }
}