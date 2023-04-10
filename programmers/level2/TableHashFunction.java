import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int[] SI = new int[data.length];

        Arrays.sort(data, (o1, o2) -> {
            if (o1[col-1] - o2[col-1] == 0) {
                return o2[0] - o1[0];
            }
            return o1[col-1] - o2[col-1];
        });

        for (int i = row_begin; i <= row_end; i++) {
            int[] tuple = data[i - 1];
            for (int value: tuple) {
                SI[i - 1] += (value % i);
            }
        }

        int answer = SI[row_begin - 1];
        for (int i = row_begin; i < row_end; i++) {
            answer ^= SI[i];
        }

        return answer;
    }
}