class Solution {
    public long solution(int n, int[] times) {

        Long max = 0L;
        for (int i = 0; i < times.length; i++) {
            max = Math.max(max, times[i]);
        }

        Long i = 0L; Long j = max * n;
        Long answer = 0L;
        while (i <= j) {
            Long mid = (i + j) / 2;

            Long tmpN = 0L;
            for (int k = 0; k < times.length; k++) {
                tmpN += (mid / times[k]);
            }

            if (n <= tmpN) {
                answer = mid;
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }

        return answer;
    }
}