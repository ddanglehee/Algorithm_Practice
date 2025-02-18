class Solution {

    int n, m;
    int k;
    int answer = 121;

    public int solution(int[][] info, int n, int m) {
        this.n = n;
        this.m = m;
        k = info.length;

        dfs(info, 0, 0, 0);

        if (answer == 121) {
            return -1;
        }

        return answer;
    }

    private void dfs(int[][] info, int i, int a, int b) {
        if (i == k) {
            answer = Math.min(answer, a);
            return;
        }

        int newA = a + info[i][0];
        int newB = b + info[i][1];
        if (newA < n) {
            dfs(info, i + 1, newA, b);
        }
        if (newB < m) {
            dfs(info, i + 1, a, newB);
        }
    }
}