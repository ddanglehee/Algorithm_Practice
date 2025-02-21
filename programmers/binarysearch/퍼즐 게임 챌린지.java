class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = 100000;
        int mid;

        int answer = 100000;
        while (start <= end) {
            mid = (start + end) / 2;

            // 계산
            long totalTime = 0;
            for (int i = 0; i < diffs.length; i++) {
                int diff = diffs[i];
                int time = times[i];

                if (diff <= mid) {
                    totalTime += time;
                } else {
                    totalTime += ((diff - mid) * (time + times[i - 1]) + time);
                }
            }

            if (totalTime <= limit) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return answer;
    }
}