class SortBasic<T : Comparable<T>> {

    fun swap(arr: MutableList<T>, idx1: Int, idx2: Int) {
        arr[idx1] = arr[idx2].also { arr[idx2] = arr[idx1] }
    }

    // O(n^2)
    fun selectionSort(arr: MutableList<T>) {
        for (i in arr.indices) {
            var minIndex = i
            for (j in i + 1 until arr.size) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j
                }
            }
            swap(arr, i, minIndex)
        }
    }

    // O(n) ~ O(n^2)
    fun insertSort(arr: MutableList<T>) {
        for (curIndex in 1..arr.lastIndex) {
            for (targetIndex in curIndex downTo 1) {
                if (arr[targetIndex] < arr[targetIndex - 1]) {
                    swap(arr, targetIndex, targetIndex - 1)
                } else {
                    break
                }
            }
        }
    }

    // O(nlogn), pivot이 배열의 첫 원소라고 할 때 이미 정렬되어있는 데이터를 입력받으면 O(n^2)
    // 매개변수로 start, end를 받아야 새로 배열을 생성할 필요 없이 구현할 수 있다.
    fun quickSort(arr: MutableList<T>, start: Int, end: Int) {
        if (end <= start) return

        val pivot = start
        var i = start + 1
        var j = end

        while (i <= j) { // i <= j 여야 i가 pivot의 값보다 큰 것을 가리킬 수 있다.
            // 양쪽 모두 arr[i] <= arr[pivot], arr[pivot] <= arr[j]여야 더 효율적이다.
            while (i <= end && arr[i] <= arr[pivot]) i++
            while (start < j && arr[pivot] <= arr[j]) j--

            if (i < j) {
                swap(arr, i, j)
            } else {
                swap(arr, pivot, j)
            }
        }

        quickSort(arr, start, j - 1)
        quickSort(arr, j + 1, end)
    }

    // O(n + k)
    // 단, 데이터의 크기 범위가 제한되어 정수 형태로 표현할 수 있을 때만 사용할 수 있다.
    fun countSort(arr: MutableList<Int>) {
        val max = arr.maxOrNull()!!
        val countList = MutableList(max + 1) { 0 }

        arr.forEach { element ->
            countList[element] = countList[element] + 1
        }

        val result = mutableListOf<Int>()
        countList.forEachIndexed { index, count ->
            for (i in 0 until count) {
                result.add(index)
            }
        }

        println(result)
    }
}