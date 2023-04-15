class Solution {
    fun solution(wallpaper: Array<String>): IntArray {
        var lux = 50
        var luy = 50
        var rdx = 0
        var rdy = 0

        for (i in wallpaper.indices) {
            for(j in wallpaper[i].indices) {
                val info = wallpaper[i][j]
                when (info) {
                    '#' -> {
                        lux = minOf(lux, i)
                        luy = minOf(luy, j)
                        rdx = maxOf(rdx, i + 1)
                        rdy = maxOf(rdy, j + 1)
                    }
                }
            }
        }

        return intArrayOf(lux, luy, rdx, rdy)
    }
}