import java.util.*;

class Split2DArray {

    int[] answer;

    public int[] solution(int n, long left, long right) {
        answer = new int[(int)(right - left + 1)];

        for (long i = left; i <= right; i++) {
            int index = (int) (i - left);
            int row = (int) (i / n);
            int column = (int) (i % n);
            answer[index] = Math.max(row, column) + 1;
        }

        return answer;
    }
}