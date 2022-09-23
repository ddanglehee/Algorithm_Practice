import java.util.*

class CaculateParkingFee {

    fun solution(fees: IntArray, records: Array<String>): IntArray {
        var answer = mutableListOf<Int>()
        val defaultTime = fees[0]; val defaultFee = fees[1]
        val extraTime = fees[2]; val extraFee = fees[3]

        // 1. records에 있는 정보 자료구조(map, priority queue)에 담기
        val parkMap = mutableMapOf<String, ParkingInfo>()
        val park = PriorityQueue<ParkingInfo>(compareBy { it.number })
        records.forEach { record ->
            val (time, number, inOut) = record.split(" ")
            val (hour, minutes) = time.split(":").map { it.toInt() }

            if (inOut == "IN") {
                if (number in parkMap) {
                    val parkingInfo = parkMap[number]!!
                    parkingInfo.inTime = hour * 60 + minutes
                    parkingInfo.isParking = true
                } else {
                    val parkingInfo = ParkingInfo(number, hour * 60 + minutes)
                    park.offer(parkingInfo)
                    parkMap[number] = parkingInfo
                }
            } else {
                val parkingInfo = parkMap[number]!!
                parkingInfo.totalTime += (hour * 60 + minutes - parkingInfo.inTime)
                parkingInfo.isParking = false
            }
        }

        while (park.isNotEmpty()) {
            val parkingInfo = park.poll()
            if (parkingInfo.isParking) {
                parkingInfo.totalTime += (23 * 60 + 59 - parkingInfo.inTime)
            }

            var fee = 0
            parkingInfo.totalTime -= defaultTime
            fee += defaultFee
            if (0 < parkingInfo.totalTime) {
                fee += ((parkingInfo.totalTime + extraTime - 1) / extraTime) * extraFee
            }

            answer.add(fee)
        }

        return answer.toIntArray()
    }

    inner class ParkingInfo(val number: String, var inTime: Int) {
        var isParking = true
        var totalTime = 0
    }
}

