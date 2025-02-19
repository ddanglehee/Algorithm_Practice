class Solution {
    public int[] solution(int n, int s) {
        if (s < n) {
            return new int[] {-1};
        }

        int[] answer = new int[n];
        int q = s / n;
        int r = s % n;
        for (int i = 0; i < n; i++) {
            answer[i] = q;
            if (n - r <= i) {
                answer[i]++;
            }
        }

        return answer;
    }
}