import java.io.*;
import java.util.*;

public class Boj1976 {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        initParent();
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int[] mArray = new int[M];
        for (int i = 0; i < M; i++) {
            mArray[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M - 1; i++) {
            int from = mArray[i]; int to = mArray[i + 1];
            if (findParent(from) != findParent(to)) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void initParent() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    private static int findParent(int x) {
        if (parent[x] != x) {
            parent[x] = findParent(parent[x]);
        }

        return parent[x];
    }

    private static void union(int x, int y) {
        x = findParent(x);
        y = findParent(y);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }
}
