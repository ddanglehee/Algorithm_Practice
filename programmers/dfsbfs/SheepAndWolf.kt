class Solution {

    var answer = 1

    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        val visited = BooleanArray(info.size)
        dfs(0, info, edges, visited, 1, 0)

        return answer
    }

    fun dfs(cur: Int, info: IntArray, edges: Array<IntArray>, visited: BooleanArray, sheep: Int, wolf: Int) {
        visited[cur] = true

        edges.forEach { (parent, child) ->
            if (visited[parent] && !visited[child]) {
                if (info[child] == 0) {
                    answer = maxOf(sheep + 1, answer)
                    dfs(child, info, edges, visited, sheep + 1, wolf)
                } else {
                    if (wolf + 1 < sheep) {
                        dfs(child, info, edges, visited, sheep, wolf + 1)
                    }
                }
            }
        }

        visited[cur] = false
    }
}