import java.io.*;
import java.util.*;

public class Boj2243 {

    static int N;
    static int S = 1048576;
    static int candy;
    static int[] tree = new int[2 * S];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                // b번째 사탕 꺼내기
                query(1, S, 1, b);
                update(1, S, 1, candy, -1);
            } else if (a == 2) {
                // b맛 사탕 넣기
                int c = Integer.parseInt(st.nextToken()); // c개
                update(1, S, 1, b, c);
            }
        }
        System.out.println(sb);
    }

    static void query(int left, int right, int node, int target) {
        if (left != right) {
            int mid = (left + right) / 2;
            int leftValue = tree[node * 2];
            if (target <= leftValue) {
                query(left, mid, node * 2, target);
            } else {
                query (mid + 1, right, node * 2 + 1, target - leftValue);
            }
        } else {
            candy = left;
            sb.append(left).append("\n");
        }
    }

    static void update(int left, int right, int node, int target, int diff) {
        // 연관 없음
        if (target < left || right < target) return;
        // 연관 있음
        tree[node] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }
}
