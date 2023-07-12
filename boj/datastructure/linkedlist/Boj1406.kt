import java.util.LinkedList

class Boj1406 {

    // 정답코드
    fun main() = with(System.`in`.bufferedReader()) {
        val input = readLine()

        val m = readLine().toInt()
        val list = LinkedList<Char>()
        for (c in input) {
            list.add(c)
        }

        val iter = list.listIterator(list.size)
        repeat(m) {
            val cmd = readLine()
            when (cmd) {
                "L" -> {
                    if (iter.hasPrevious()) iter.previous()
                }
                "D" -> {
                    if (iter.hasNext()) iter.next()
                }
                "B" -> {
                    if (iter.hasPrevious()) {
                        iter.previous()
                        iter.remove()
                    }
                }
                else -> {
                    iter.add(cmd[2])
                }
            }
        }

        print(list.joinToString(""))
    }

    // 시간초과 코드 (비교해보세요~~)
    fun main() = with(System.`in`.bufferedReader()) {
        val input = readLine()
        val m = readLine().toInt()

        val li = LinkedList<Char>()
        for (c in input) {
            li.add(c)
        }
        var cursor = input.length // cursor를 매번 index로 접근하게 되기 때문에 "B"와 "P" 연산에서 O(li의 크기)의 시간복잡도를 가진다.
        repeat(m) {
            val cmd = readLine()
            when (cmd) {
                "L" -> {
                    if (0 < cursor) cursor--
                }
                "D" -> {
                    if (cursor != li.size) cursor++
                }
                "B" -> {
                    if (0 < cursor) li.removeAt(cursor-- - 1)
                }
                else -> {
                    li.add(cursor++, cmd[2])
                }
            }
        }

        print(li.joinToString(""))
    }
}