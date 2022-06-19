package kruskal

import java.util.PriorityQueue

class PlanOfDividingCity {

    private lateinit var city: Array<Int>

    fun solution() {
        val br = System.`in`.bufferedReader()
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        city = Array(n + 1) { it }

        val priorityQueue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        repeat(m) {
            val (a, b, c) = br.readLine().split(" ").map { it.toInt() }
            priorityQueue.offer(Triple(a, b, c))
        }

        var totalCost = 0
        var lastCost = 0
        while (priorityQueue.isNotEmpty()) {
            val (a, b, c) = priorityQueue.poll()
            val hasCycle = !unionCity(a, b)

            if (!hasCycle) {
                totalCost += c
                lastCost = c
            }
        }

        println(totalCost - lastCost)
    }

    private fun unionCity(a: Int, b: Int): Boolean {
        val aCity = findCity(a)
        val bCity = findCity(b)

        if (aCity < bCity) {
            city[bCity] = aCity
        } else if (aCity > bCity) {
            city[aCity] = bCity
        } else {
            return false
        }
        return true
    }

    private fun findCity(x: Int): Int {
        if (city[x] != x) {
            city[x] = findCity(city[x])
        }
        return city[x]
    }
}