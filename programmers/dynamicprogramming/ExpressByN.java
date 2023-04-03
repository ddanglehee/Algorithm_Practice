import java.util.*;

class ExpressByN {

    ArrayList<Integer>[] dp = new ArrayList[9];

    public int solution(int N, int number) {
        int answer = -1;
        if (N == number) return 1;

        dp[1] = new ArrayList<Integer>();
        dp[1].add(N);
        for (int i = 2; i <= 8; i++) {
            dp[i] = new ArrayList<Integer>();
            if (fillDpTable(i, N, number)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    boolean fillDpTable(int i, int n, int number) {
        int tmp = 0;
        for (int k = i - 1; 0 <= k; k--) {
            tmp += Math.pow(10, k) * n;
        }
        if (tmp == number) return true;
        dp[i].add(tmp);

        for (int k = 1; k < i; k++) {
            for (int a: dp[k]) {
                for (int b: dp[i - k]) {
                    int add = a + b;
                    int sub = a - b;
                    int mul = a * b;

                    if (add == number || sub == number || mul == number) return true;
                    dp[i].add(add);
                    dp[i].add(sub);
                    dp[i].add(mul);
                    if (b != 0) {
                        int div = a / b;
                        if (div == number) return true;
                        dp[i].add(div);
                    }
                }
            }
        }

        return false;
    }
}