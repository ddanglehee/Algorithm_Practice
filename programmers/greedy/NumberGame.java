import java.util.*;

class NumberGame {
    public int solution(int[] A, int[] B) {
        int answer = 0;

        int N = A.length;
        Arrays.sort(A);
        Arrays.sort(B);

        int i = N - 1; int j = N - 1;
        while (0 <= i && 0 <= j) {
            if (A[i] < B[j]) {
                answer++;
                i--;j--;
            } else {
                i--;
            }
        }

        return answer;
    }
}