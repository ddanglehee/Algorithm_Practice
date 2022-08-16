class MinRectangle {
    fun solution(sizes: Array<IntArray>): Int {
        var maxWidth = 0
        var maxHeight = 0

        sizes.forEachIndexed { index, size ->
            var width = size[0]
            var height = size[1]

            if (width < height) {
                val tmp = width
                width = height
                height = tmp
            }

            maxWidth = maxWidth.coerceAtLeast(width)
            maxHeight = maxHeight.coerceAtLeast(height)
        }

        return maxWidth * maxHeight
    }
}