import java.util.*;
import java.io.*;

public class Solution {

    static int N;
    static boolean[] tmp;
    static int[] memo = new int[100000];
    static ArrayList<boolean[]>[] splitPointList = new ArrayList[6]; // 쪼개는 곳 조합 리스트

    public static void main(String[] args) throws IOException {
        initSplitPointList();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());

            Arrays.fill(memo, -1);
            memo[N] = numberGame(Integer.toString(N));

            sb.append("#").append(t).append(" ").append(memo[N]).append("\n");
        }

        System.out.print(sb);
    }

    static int numberGame(String n) {
        int num = Integer.parseInt(n);

        if (num < 10) return 0;
        if (memo[num] != -1) return memo[num];

        for (boolean[] split : splitPointList[n.length()]) {
            int result = 1;
            int tmp = 0;

            for (int i = 0; i < n.length(); i++) {
                tmp *= 10;
                tmp += n.charAt(i) - '0';

                if (i == n.length() - 1 || split[i]) {
                    result *= tmp;
                    tmp = 0;
                }
            }

            if (result == num) continue;

            memo[num] = Math.max(memo[num], numberGame(Integer.toString(result)) + 1);
        }

        return memo[num];
    }

    static void initSplitPointList() {
        for (int i = 0; i <= 5; i++) {
            splitPointList[i] = new ArrayList<>();
        }

        for (int size = 1; size <= 4; size++) {
            tmp = new boolean[size];
            subset(tmp, 0, size);
        }
    }

    static void subset(boolean[] arr, int index, int size) {
        if (index == size) {
            splitPointList[size + 1].add(Arrays.copyOf(arr, size));
            return;
        }

        arr[index] = false;
        subset(arr, index + 1, size);
        arr[index] = true;
        subset(arr, index + 1, size);
    }
}