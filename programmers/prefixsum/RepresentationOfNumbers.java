class Solution {
    public int solution(int n) {
        int[] sum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + i;
        }

        int i = 0;
        int j = 1;
        int answer = 0;
        while (j <= n && i <= j) {
            int tmp = sum[j] - sum[i];

            if (tmp < n) {
                j++;
            } else if (tmp == n) {
                answer++;
                j++;
            } else {
                i++;
            }
        }

        return answer;
    }
}