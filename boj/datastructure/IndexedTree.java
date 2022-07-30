import java.io.*;
import java.util.*;

// 주석을 외우자! 그림으로!
public class IndexedTree {

    static int N, M, K;
    static long[] nums;
    static long[] tree;

    static int S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new long[N + 1];

        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(br.readLine());
        }

        // tree 만들기
        S = 1;
        while (S < N) {
            S *= 2;
        }
        tree = new long[2 * S];
    }

    static void initBU() {
        // leaf 채우기
        for (int i = 0; i < N; i++) {
            tree[S + i] = nums[i + 1];
        }
        // 내부노드 채움
        for (int i = S - 1; 0 < i; i--) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    static long queryTD(int left, int right, int node, int queryLeft, int queryRight) {
        // 연관이 없음 -> 결과에 영향이 없는 값 return
        if (queryRight < left || right < queryLeft) {
            return 0;
        }
        // 판단 가능 -> 현재 노드 값 return
        else if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }
        // 판단 불가, 자식에게 위임, 자식에서 올라온 합을 return
        else {
            int mid = (left + right) / 2;
            long resultLeft = queryTD(left, mid, node * 2, queryLeft, queryRight);
            long resultRight = queryTD(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return resultLeft + resultRight;
        }
    }

    static void updateTD(int left, int right, int node, int target, int diff) {
        // 연관 없음
        if (target < left || right < target) {
            return;
        }
        // 연관 있음 -> 현재 노드에 diff 반영 -> 자식에게 diff 전달
        else {
            tree[node] += diff;
            // 자신이 leaf인지 아닌지 알아야됨
            if (left != right) {
                int mid = (left + right) / 2;
                updateTD(left, mid, node * 2, target, diff);
                updateTD(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    static long queryBU(int queryLeft, int queryRight) {
        // leaf에서 left, right 설정
        int left = S + queryLeft - 1;
        int right = S + queryRight - 1;

        long sum = 0;
        while (left <= right) {
            // 좌측 노드가 홀수(오른쪽노드)이면 현재 노드 값 사용하고 한 칸 옆으로
            if (left % 2 == 1) {
                sum += tree[left++];
            }
            // 우측 노드가 짝수(왼쪽노드)이면 현재 노드 값 사용하고 한 칸 옆으로
            if (right % 2 == 0) {
                sum += tree[right--];
            }
            // 좌측, 우측 모두 부모로 이동
            left /= 2;
            right /= 2;
        }

        return sum;
    }

    static void updateBU(int target, long value) {
        // leaf에서 target을 찾음
        int node = S + target - 1;
        // value 반영
        tree[node] = value;
        // root에 도달할 때까지 부모에 값 반영
        node /= 2;
        while (0 < node) {
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
            node /= 2;
        }
    }
}
