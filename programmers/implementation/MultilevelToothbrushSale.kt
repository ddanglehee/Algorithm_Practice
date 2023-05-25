class Solution {
    fun solution(enroll: Array<String>, referral: Array<String>, seller: Array<String>, amount: IntArray): IntArray {
        val employeeMap = mutableMapOf<String, Information>()

        for (i in enroll.indices) {
            val employee = enroll[i]
            val referaler = referral[i]

            employeeMap[employee] = Information(referaler)
        }

        for (i in seller.indices) {
            val employee = seller[i]
            val income = amount[i] * 100

            distributeIncome(income, employee, employeeMap)
        }

        val answer = IntArray(enroll.size)
        enroll.forEachIndexed { i, employee ->
            answer[i] = employeeMap[employee]!!.income
        }

        return answer
    }

    fun distributeIncome(income: Int, employee: String, employeeMap: Map<String, Information>) {
        val distributed = (income * 0.1).toInt()
        employeeMap[employee]!!.income += income - distributed
        if (distributed == 0) return

        val referaler = employeeMap[employee]!!.referaler
        if (referaler == "-") return

        distributeIncome(distributed, referaler, employeeMap)
    }
}

data class Information(val referaler: String) {
    var income = 0
}