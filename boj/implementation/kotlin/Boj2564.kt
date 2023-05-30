import kotlin.math.abs

class Boj2564 {

    fun main() = with(System.`in`.bufferedReader()) {
        val (c, r) = readLine().split(" ").map { it.toInt() }
        val shopCount = readLine().toInt()
        val shopList = mutableListOf<Pair<Int, Int>>()

        repeat(shopCount) {
            val (d, k) = readLine().split(" ").map { it.toInt() }
            shopList.add(d to k)
        }
        val (dongD, dongK) = readLine().split(" ").map { it.toInt() }

        var answer = 0
        shopList.forEach { (d, k) ->
            when (dongD) {
                1 -> {
                    when (d) {
                        1 -> {
                            answer += abs(dongK - k)
                        }
                        2 -> {
                            answer += minOf(dongK + k, 2 * c - dongK - k) + r
                        }
                        3 -> {
                            answer += dongK + k
                        }
                        4 -> {
                            answer += c - dongK + k
                        }
                    }
                }
                2 -> {
                    when (d) {
                        1 -> {
                            answer += minOf(dongK + k, 2 * c - dongK - k) + r
                        }
                        2 -> {
                            answer += abs(dongK - k)
                        }
                        3 -> {
                            answer += dongK + r - k
                        }
                        4 -> {
                            answer += c - dongK + r - k
                        }
                    }
                }
                3 -> {
                    when (d) {
                        1 -> {
                            answer += dongK + k
                        }
                        2 -> {
                            answer += r - dongK + k
                        }
                        3 -> {
                            answer += abs(dongK - k)
                        }
                        4 -> {
                            answer += minOf(dongK + k, 2 * r - dongK - k) + c
                        }
                    }
                }
                4 -> {
                    when (d) {
                        1 -> {
                            answer += dongK + c - k
                        }
                        2 -> {
                            answer += r - dongK + c - k
                        }
                        3 -> {
                            answer += minOf(dongK + k, 2 * r - dongK - k) + c
                        }
                        4 -> {
                            answer += abs(dongK - k)
                        }
                    }
                }
            }
        }
        print(answer)
    }
}