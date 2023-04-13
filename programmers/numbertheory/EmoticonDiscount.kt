import java.util.ArrayList

class Solution {

    var emoticonPlusCount = 0;
    var totalMoney = 0;
    val discount = intArrayOf(10, 20, 30, 40);

    fun solution(users: Array<IntArray>, emoticons: IntArray): IntArray {
        var answer: IntArray = IntArray(2)

        permutation(emoticons.size, arrayListOf<Int>(), users, emoticons)

        answer[0] = emoticonPlusCount; answer[1] = totalMoney
        return answer
    }

    private fun calculateAnswer(discountList: ArrayList<Int>, users: Array<IntArray>, emoticons: IntArray) {
        var count = 0
        var money = 0

        users.forEach { (discount, price) ->

            var sum: Double = 0.0

            for (i in emoticons.indices) {
                if (discountList[i] < discount) continue
                sum += (emoticons[i] - (emoticons[i] * discountList[i] * 0.01))

                if (price <= sum) {
                    count++
                    sum = 0.0
                    break
                }
            }

            money += sum.toInt()
        }

        if (emoticonPlusCount < count) {
            emoticonPlusCount = count
            totalMoney = money
        } else if (emoticonPlusCount == count && totalMoney < money) {
            totalMoney = money
        }
    }

    private fun permutation(count: Int, discountList: ArrayList<Int>, users: Array<IntArray>, emoticons: IntArray) {
        if (count == discountList.size) {
            calculateAnswer(discountList, users, emoticons)
            return
        }

        for (i in 0 until 4) {
            discountList.add(discount[i])
            permutation(count, discountList, users, emoticons)
            discountList.removeAt(discountList.lastIndex)
        }
    }
}