import java.util.*;
import java.io.*;

public class Boj14888 {

    static int n;
    static int[] operand;
    static ArrayList<Integer> operatorList = new ArrayList<>();
    static boolean[] isUsedOperator;
    static int max = -1000000000;
    static int min = 1000000000;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < operatorList.size(); i++) {
            isUsedOperator[i] = true;
            backtracking(operand[0], i, 1);
            isUsedOperator[i] = false;
        }

        sb.append(max).append("\n").append(min);
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        operand = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int op = Integer.parseInt(st.nextToken());
            operand[i] = op;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            int count = Integer.parseInt(st.nextToken());
            for (int c = 0; c < count; c++) {
                operatorList.add(i);
            }
        }
        isUsedOperator = new boolean[operatorList.size()];
    }

    private static void backtracking(int tmp, int operatorIndex, int operandIndex) {
        if (operandIndex == n - 1) {
            int result = calculate(tmp, operatorIndex, operandIndex);
            max = Math.max(result, max);
            min = Math.min(result, min);
            return;
        }

        for (int i = 0; i < operatorList.size(); i++) {
            if (isUsedOperator[i]) continue;
            isUsedOperator[i] = true;
            backtracking(calculate(tmp, operatorIndex, operandIndex), i, operandIndex + 1);
            isUsedOperator[i] = false;
        }
    }

    private static int calculate(int tmp, int operatorIndex, int operandIndex) {
        int operator = operatorList.get(operatorIndex);

        if (operator == 0) {
            return tmp + operand[operandIndex];
        } else if (operator == 1) {
            return tmp - operand[operandIndex];
        } else if (operator == 2) {
            return tmp * operand[operandIndex];
        } else {
            return tmp / operand[operandIndex];
        }
    }
}
