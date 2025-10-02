import java.util.*;

class Solution {
    public int solution(String arr[]) {
        int n = arr.length;
        int[][] max = new int[n / 2 + 1][n / 2 + 1];
        int[][] min = new int[n / 2 + 1][n / 2 + 1];
        for (int i = 0; i < n / 2 + 1; i++) {
            Arrays.fill(max[i], Integer.MIN_VALUE);
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n / 2 + 1; i++) {
            max[i][i] = Integer.parseInt(arr[i * 2]);
            min[i][i] = Integer.parseInt(arr[i * 2]);
        }

        for (int c = 1; c < n / 2 + 1; c++) { // 피연산자 개수
            for (int i = 0; i + c < n / 2 + 1; i++) { // 시작 피연산자
                int j = i + c;
                for (int k = i; k < j; k++) { // 중간 피연산자
                    if (arr[2 * k + 1].equals("+")) {
                        max[i][j] = Math.max(max[i][j], max[i][k] + max[k + 1][j]);
                        min[i][j] = Math.min(min[i][j], min[i][k] + min[k + 1][j]);
                    } else {
                        max[i][j] = Math.max(max[i][j], max[i][k] - min[k + 1][j]);
                        min[i][j] = Math.min(min[i][j], min[i][k] - max[k + 1][j]);
                    }
                }
            }
        }

        return max[0][n / 2];
    }
}