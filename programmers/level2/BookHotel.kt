class BookHotel {
    fun solution(book_time: Array<Array<String>>): Int {
        val room = mutableListOf<BookInfo>()
        val bookInfos = mutableListOf<BookInfo>()

        book_time.forEach { book ->
            val (startH, startM) = book[0].split(":").map { it.toInt() }
            var (endH, endM) = book[1].split(":").map { it.toInt() }

            if (50 <= endM) {
                endH++
                endM %= 10
            } else {
                endM += 10
            }
            bookInfos.add(BookInfo(startH, startM, endH, endM))
        }
        bookInfos.sortWith(compareBy<BookInfo> { it. startH }.thenBy { it.startM })

        bookInfos.forEach { bookInfo ->
            var addRoom = true
            for (i in 0 until room.size) {
                val usingInfo = room[i]

                if (usingInfo.endH < bookInfo.startH || (usingInfo.endH == bookInfo.startH && usingInfo.endM <= bookInfo.startM)) {
                    room[i] = bookInfo
                    addRoom = false
                    break
                }
            }

            if (addRoom) room.add(bookInfo)
        }

        return room.size
    }
}

data class BookInfo(val startH: Int, val startM: Int, val endH: Int, val endM: Int)