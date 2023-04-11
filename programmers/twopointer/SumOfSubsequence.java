class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int length = 1000001;
        int start = 0;
        int end = 0;
        int sum = sequence[0];

        while (true) {
            if (sum == k) {
                if (end - start + 1 < length) {
                    answer[0] = start;
                    answer[1] = end;
                    length = end - start + 1;
                }
            }

            if (sum <= k) {
                end++;
                if (sequence.length <= end) {
                    break;
                }
                sum += sequence[end];
            } else {
                sum -= sequence[start];
                start++;
                if (sequence.length < start) {
                    break;
                }
            }
        }

        return answer;
    }
}