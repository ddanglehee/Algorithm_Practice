import java.util.LinkedList

class Solution {
    fun solution(n: Int, k: Int, cmd: Array<String>): String {
        var list = Node(0)
        var cur = list

        // 표 초기화
        var last = list
        for (i in 1 until n) {
            val newNode = Node(i)
            if (i == k) cur = newNode

            last.next = newNode
            newNode.prev = last
            last = newNode
        }

        // cmd 처리하기
        val dStack = mutableListOf<Node>()
        cmd.forEach { command ->
            when(command) {
                "C" -> {
                    dStack.add(cur)
                    if (cur.prev == null) {
                        cur.next!!.prev = null
                        cur = cur.next!!
                    } else if (cur.next == null) {
                        cur.prev!!.next = null
                        cur = cur.prev!!
                    } else {
                        cur.prev!!.next = cur.next!!
                        cur.next!!.prev = cur.prev!!
                        cur = cur.next!!
                    }
                }
                "Z" -> {
                    val dNode = dStack.removeLast()
                    if (dNode.prev == null) {
                        dNode.next!!.prev = dNode
                    } else if (dNode.next == null) {
                        dNode.prev!!.next = dNode
                    } else {
                        dNode.prev!!.next = dNode
                        dNode.next!!.prev = dNode
                    }
                }
                else -> {
                    val (c, count) = command.split(" ")
                    if (c == "U") {
                        repeat(count.toInt()) {
                            cur = cur.prev!!
                        }
                    } else {
                        repeat(count.toInt()) {
                            cur = cur.next!!
                        }
                    }
                }
            }
        }

        // 정답
        val answer = Array<String>(n) {
            "X"
        }
        var curNode: Node? = cur
        while (curNode != null) {
            answer[curNode.n] = "O"
            curNode = curNode.prev
        }
        curNode = cur
        while (curNode != null) {
            answer[curNode.n] = "O"
            curNode = curNode.next
        }

        return answer.joinToString("")
    }
}

data class Node(val n: Int) {
    var prev: Node? = null
    var next: Node? = null
}