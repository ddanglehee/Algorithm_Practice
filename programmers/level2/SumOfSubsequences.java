import java.util.*;

class SumOfSubsequences {

    private Set<Integer> numberSet = new HashSet<>();
    private int n;
    private int[] dp;

    public int solution(int[] elements) {
        initDpTable(elements);
        countSum(elements);
        return numberSet.size();
    }

    private void initDpTable(int[] elements) {
        n = elements.length;
        dp = new int[n]; // dp[i]는 elements의 인덱스 0 ~ i까지의 요소의 합

        dp[0] = elements[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + elements[i];
        }
    }

    private void countSum(int[] elements) {
        int start = 0; int end = 1;

        while (start < n) {
            int endIndex = end % n;
            if (start == endIndex) {
                numberSet.add(elements[start]);
                start++; end = start;
            } else if (start < endIndex) {
                numberSet.add(dp[endIndex] - dp[start] + elements[start]);
            } else {
                numberSet.add(dp[endIndex] + dp[n - 1] - dp[start] + elements[start]);
            }
            end++;
        }
    }
}
