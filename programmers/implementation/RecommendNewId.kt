class RecommendNewId {

    fun solution(newId: String): String =
        if (newId == "") {
            "aaa"
        } else {
            newId.first().second().third().fourth().fifth().sixth().seventh()
        }

    private fun String.first(): String =
        this.lowercase()


    private fun String.second(): String =
        Regex("[^a-z0-9_.-]").replace(this, "")


    private fun String.third(): String =
        this.replace(Regex("[.][.]*"), ".")


    private fun String.fourth(): String =
        this.removeSuffix(".").removePrefix(".")

    private fun String.fifth(): String =
        if (this.isEmpty()) {
            "a"
        } else {
            this
        }


    private fun String.sixth(): String =
        if (15 < this.length) {
            this.substring(0, 15).fourth()
        } else {
            this
        }


    private fun String.seventh(): String =
        if (this.length < 3) {
            var string = this.toString()
            val c = this.last()
            while (string.length < 3) {
                string += c
            }
            string
        } else {
            this
        }
}