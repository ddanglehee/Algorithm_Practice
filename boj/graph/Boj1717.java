import java.io.*;
import java.util.*;

public class Boj1717 {

    static int N, M;
    static int[] parent;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 합집합
            if (command == 0) {
                union(a, b);
            }
            // 두 원소가 같은 집합인지
            else {
                int parentA = find(a);
                int parentB = find(b);
                if (parentA == parentB) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }
}
