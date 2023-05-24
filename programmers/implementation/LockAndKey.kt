class LockAndKey {
    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val n = lock.size
        val m = key.size
        val l = n + 2 * (m - 1)
        val graph = Array(l) { IntArray(l) }
        val lockRange = (m - 1) until (m + n - 1)
        for (i in 0 until n) {
            for (j in 0 until n) {
                graph[m + i - 1][m + j - 1] = lock[i][j]
            }
        }

        for (r in 0 until 4) {
            rotate(m, key)

            for (i in 0..(l - m)) {
                for (j in 0..(l - m)) {

                    for (ki in 0 until m) {
                        for (kj in 0 until m) {
                            if (!(i + ki in lockRange && j + kj in lockRange)) continue
                            graph[i + ki][j + kj] += key[ki][kj]
                        }
                    }

                    // 현재 key로 좌물쇠를 열 수 있는지 확인
                    var correct = true
                    for (li in lockRange) {
                        for (lj in lockRange) {
                            if (graph[li][lj] != 1) {
                                correct = false
                                break
                            }
                        }
                        if (!correct) break
                    }
                    if (correct) return true

                    // graph 원상복구
                    for (ki in 0 until m) {
                        for (kj in 0 until m) {
                            if (!(i + ki in lockRange && j + kj in lockRange)) continue
                            graph[i + ki][j + kj] -= key[ki][kj]
                        }
                    }
                }
            }
        }

        return false
    }

    // 90도 시계 방향으로 회전
    private fun rotate(m: Int, key: Array<IntArray>) {
        val tmpKey = Array(m) {
            IntArray(m)
        }

        for (i in 0 until m) {
            for (j in 0 until m) {
                tmpKey[j][m - i - 1] = key[i][j]
            }
        }

        for (i in 0 until m) {
            for (j in 0 until m) {
                key[i][j] = tmpKey[i][j]
            }
        }
    }
}