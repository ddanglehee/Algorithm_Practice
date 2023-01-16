class DeliverAndPickup {
    var answer: Long = 0
    var deliverEnd = -1; var pickupEnd = -1

    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        endPointerInit(n, deliveries, pickups)
        work(cap, deliveries, pickups)

        return answer
    }

    fun endPointerInit(n: Int, deliveries: IntArray, pickups: IntArray) {
        for (i in n - 1 downTo 0) {
            if (deliveries[i] == 0) continue
            deliverEnd = i
            break
        }
        for (i in n - 1 downTo 0) {
            if (pickups[i] == 0) continue
            pickupEnd = i
            break
        }
    }

    fun work(cap: Int, deliveries: IntArray, pickups: IntArray) {
        while (0 <=  deliverEnd || 0 <= pickupEnd) {
            val dest = maxOf(deliverEnd, pickupEnd)
            var deliverCount = cap; var pickupCount = cap

            for (i in dest downTo 0) {
                if (deliverCount == 0 && pickupCount == 0) break

                val deliver = minOf(deliveries[i], deliverCount)
                val pickup = minOf(pickups[i], pickupCount)
                deliveries[i] -= deliver; deliverCount -= deliver
                pickups[i] -= pickup; pickupCount -= pickup
            }
            while (0 <= deliverEnd && deliveries[deliverEnd] == 0) deliverEnd--
            while (0 <= pickupEnd && pickups[pickupEnd] == 0) pickupEnd--

            answer += (dest + 1) * 2
        }
    }
}