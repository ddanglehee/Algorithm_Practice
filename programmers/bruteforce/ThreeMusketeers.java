class ThreeMusketeers {

    private int studentCount;
    private int answer = 0;

    public int solution(int[] number) {
        studentCount = number.length;

        makeThreeMusketeers(0, 0, 0, number);

        return answer;
    }

    private void makeThreeMusketeers(int depth, int sum, int startIndex, int[] number) {
        if (depth == 3) {
            if (sum == 0) {
                answer++;
            }
            return;
        }

        for (int i = startIndex; i < studentCount; i++) {
            makeThreeMusketeers(depth + 1, sum + number[i], i + 1, number);
        }
    }
}