class TestingPersonalityType {

    private val typeMap = mutableMapOf(
        'R' to 0, 'T' to 0,
        'C' to 0, 'F' to 0,
        'J' to 0, 'M' to 0,
        'A' to 0, 'N' to 0
    )

    fun solution(survey: Array<String>, choices: IntArray): String {
        scoreType(survey, choices)
        return calculateResult()
    }

    private fun scoreType(survey: Array<String>, choices: IntArray) {
        for (i in survey.indices) {
            val type = survey[i]
            val choice = choices[i]

            if (choice in 1..3) {
                typeMap[type[0]] = typeMap[type[0]]!! + 4 - choice
            } else {
                typeMap[type[1]] = typeMap[type[1]]!! + choice - 4
            }
        }
    }

    private fun calculateResult(): String {
        var result = ""
        val R = typeMap['R']!!; val T = typeMap['T']!!
        val C = typeMap['C']!!; val F = typeMap['F']!!
        val J = typeMap['J']!!; val M = typeMap['M']!!
        val A = typeMap['A']!!; val N = typeMap['N']!!

        result += if (R < T) "T" else "R"
        result += if (C < F) "F" else "C"
        result += if (J < M) "M" else "J"
        result += if (A < N) "N" else "A"

        return result
    }
}