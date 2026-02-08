import java.io.*;
import java.util.*;

public class Solution {

    static int N, X, M;
    static Condition[] conditions = new Condition[10];
    static ArrayList<int[]> candidates = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            init();
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());
                conditions[i] = new Condition(l, r, s);
            }

            for (int[] condition : conditions[0].list) {
                int[] tmp = new int[N];
                for (int i = 0; i < N; i++) {
                    tmp[i] = -1;
                }
                for (int i = conditions[0].l; i <= conditions[0].r; i++) {
                    tmp[i] = condition[i - conditions[0].l];
                }
                solution(1, tmp);
            }

            sb.append("#").append(t).append(" ");
            if (candidates.size() == 0) {
                sb.append("-1");
            } else {
                Collections.sort(candidates, (o1, o2) -> {
                    int sum1 = calculateSum(o1);
                    int sum2 = calculateSum(o2);

                    if (sum1 != sum2) return sum2 - sum1;

                    for (int i = 0; i < N; i++) {
                        if (o1[i] != o2[i]) return o1[i] - o2[i];
                    }
                    return 0;
                });
                for (int i : candidates.get(0)) {
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static int calculateSum(int[] arr) {
        int sum = 0;

        for (int i = 0; i < N; i++) {
            sum += arr[i];
        }

        return sum;
    }

    static void solution(int m, int[] tmp) {
        if (m == M) {
            int[] candidate = tmp.clone();
            for (int i = 0; i < candidate.length; i++) {
                if (candidate[i] == -1) {
                    candidate[i] = X;
                }
            }
            candidates.add(candidate);
            return;
        }

        Condition curCondition = conditions[m];
        for (int[] arr : curCondition.list) { // 햄스터 수 가능한 경우의 수
            boolean isCorrect = true;
            for (int i = curCondition.l; i <= curCondition.r; i++) {
                if (tmp[i] == -1 || tmp[i] == arr[i - curCondition.l]) continue;

                isCorrect = false;
                break;
            }

            if (isCorrect) {
                int[] origin = new int[curCondition.count];
                for (int i = curCondition.l; i <= curCondition.r; i++) {
                    origin[i - curCondition.l] = tmp[i];
                }
                for (int i = curCondition.l; i <= curCondition.r; i++) {
                    tmp[i] = arr[i - curCondition.l];
                }
                solution(m + 1, tmp);
                for (int i = curCondition.l; i <= curCondition.r; i++) {
                    tmp[i] = origin[i - curCondition.l];
                }
            }
        }
    }

    static void init() {
        candidates.clear();
    }

    static class Condition {

        int l, r, s;
        int count;
        ArrayList<int[]> list = new ArrayList<>();

        Condition(int l, int r, int s) {
            this.l = l;
            this.r = r;
            this.s = s;
            count = r - l + 1;

            permutation(count, 0, 0, new int[count]);
        }

        private void permutation(int count, int index, int sum, int[] arr) {
            if (count == index) {
                if (sum == s) this.list.add(Arrays.copyOf(arr, count));
                return;
            }

            for (int i = 0; i <= X; i++) {
                if (s < sum + i) continue;
                arr[index] = i;
                permutation(count, index + 1, sum + i, arr);
            }
        }
    }
}

