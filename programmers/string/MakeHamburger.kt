class MakeHamburger {
    fun solution(ingredients: IntArray): Int {
        var answer: Int = 0

        val stack = ArrayDeque<Int>()
        ingredients.forEach { ingredient ->
            stack.add(ingredient)

            if (4 <= stack.size) {
                if (stack[stack.size - 4] == 1
                    && stack[stack.size - 3] == 2
                    && stack[stack.size - 2] == 3
                    && stack[stack.size - 1] == 1)
                {
                    answer++
                    repeat(4){
                        stack.removeLast();
                    }
                }
            }
        }

        return answer
    }
}