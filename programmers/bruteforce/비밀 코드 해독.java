class Solution {
    int answer = 0;
    int n, m;
    int[][] q;
    int[] ans;

    public int solution(int n, int[][] q, int[] ans) {
        m = q.length;
        this.n = n;
        this.q = q;
        this.ans = ans;
        solve(0, 1, new int[5]);
        return answer;
    }

    private void solve(int index, int start, int[] arr) {
        for (int i = start; i <= n; i++) {
            arr[index] = i;
            if (index == 4) {
                if (check(arr)) answer++;
            } else {
                solve(index + 1, i + 1, arr);
            }
        }
    }

    private boolean check(int[] arr) {
        int[] cmp = new int[m];
        // q 확인
        int qi;
        int ai;
        for (int i = 0; i < m; i++) {
            qi = 0;
            ai = 0;
            while (qi < 5 && ai < 5) {
                if (q[i][qi] == arr[ai]) {
                    cmp[i]++;
                    qi++;
                    ai++;
                } else if (q[i][qi] < arr[ai]) {
                    qi++;
                } else {
                    ai++;
                }
            }
        }

        // answer 확인
        boolean isAnswer = true;
        for (int i = 0; i < m; i++) {
            if(arr[0] == 3 && arr[1] == 5 && arr[2] == 7 && arr[3] == 8 && arr[4] == 9) {
                System.out.println(i + ": " + cmp[i]);
            }

            if (ans[i] != cmp[i]) {
                isAnswer = false;
                break;
            }
        }


        return isAnswer;
    }
}