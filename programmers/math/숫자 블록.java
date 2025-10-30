import java.util.Arrays;

class Solution {
    public int[] solution(long begin, long end) {
        int b = (int) begin;
        int e = (int) end;
        int[] answer = new int[e - b + 1];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = getMaxYaksu(i + b);
        }

        if (begin == 1L) {
            answer[0] = 0;
        }

        return answer;
    }

    private int getMaxYaksu(int n) {
        int max = 1;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                max = i;
                if (n / i <= 10000000) {
                    return n / i;
                }
            }
        }
        return max;
    }
}