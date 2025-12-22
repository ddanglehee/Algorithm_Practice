import java.util.ArrayList;

class Solution {

    public int[] solution(int n, long k) {
        int[] answer = new int[n];

        ArrayList<Integer> list = new ArrayList<>();
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            list.add(i);
            factorial[i] = factorial[i-1] * i;
        }

        k--;
        for (int i = 0; i < n; i++) {
            int index = (int) (k / factorial[n - i - 1]);
            answer[i] = list.get(index);
            list.remove(index);
            k %= factorial[n - i - 1];
        }

        return answer;
    }
}