import java.util.*;
import java.io.*;

public class Boj2042 {

    static int N, M, K;
    static int S;
    static long[] data;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        data = new long[N + 1];
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[S * 2];

        for (int i = 1; i <= N; i++) {
            long tmp = Long.parseLong(br.readLine());
            data[i] = tmp;
        }

        init();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(1, S, 1, b, c - tree[S + b - 1]);
            } else if (a == 2) {
                sb.append(query(1, S, 1, b, c)).append("\n");
            }
        }

        System.out.println(sb);
    }

    static void init() {
        // leaf에 값 반영하기
        for (int i = 0; i < N; i++) {
            tree[S + i] = data[i + 1];
        }
        // 내부 노드에 값 반영하기
        for (int i = S - 1; 0 < i; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, long queryRight) {
        // 연관 없음
        if (queryRight < left || right < queryLeft) return 0;
        // 판단 가능
        else if (queryLeft <= left && right <= queryRight) return tree[node];
        // 판단 불가
        else {
            int mid = (left + right) / 2;
            long resultLeft = query(left, mid, node * 2, queryLeft, queryRight);
            long resultRight = query(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return resultLeft + resultRight;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        // 연관 없음
        if (target < left || right < target) return;
        // 연관 있음
        tree[node] += diff;
        // 연관 있는데 리프노드가 아니면
        if (left != right) {
            int mid = (left + right) / 2;
            update(left, mid, node * 2, target, diff);
            update(mid + 1, right, node * 2 + 1, target, diff);
        }
    }
}
