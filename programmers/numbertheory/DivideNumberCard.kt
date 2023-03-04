class DivideNumberCard {

    var answer: Int = 0

    fun solution(arrayA: IntArray, arrayB: IntArray): Int {
        if (arrayA.size == 1) {
            return if (arrayA[0] == arrayB[0]) 0 else maxOf(arrayA[0], arrayB[0])
        }

        arrayA.sortDescending(); arrayB.sortDescending() // 순차적으로 gcd 구하기 편하게 내림차순으로 정렬
        return maxOf(findA(arrayA, arrayB), findA(arrayB, arrayA))
    }

    private fun findA(arr1: IntArray, arr2: IntArray): Int {
        var gcd = getGcd(arr1[0], arr1[1])
        if (gcd == 1) return 0

        // arr1에 있는 모든 원소의 최대공약수 구하기
        for (i in 2 until arr1.size) {
            gcd = getGcd(gcd, arr1[i])
            if (gcd == 1) return 0
        }

        // arr1의 최대공약수로 나눌 수 있는 arr2가 있는지 확인
        for (number in arr2) {
            if (number % gcd == 0) return 0
        }

        return gcd
    }

    private fun getGcd(a: Int, b: Int): Int {
        var x = a; var y = b

        while (y != 0) {
            val r = x % y
            x = y
            y = r
        }

        return x
    }
}