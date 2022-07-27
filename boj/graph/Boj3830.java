import java.io.*;
import java.util.*;

public class Boj3830 {

    static int N, M;
    static int[] parent;
    static long[] weightDiff; // parent가 더 무거우면 양수, parent가 가벼우면 음수
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            parent = new int[N + 1];
            weightDiff = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

            if (N == 0 && M == 0) break;
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                char command = st.nextToken().charAt(0);
                if (command == '!') {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    long w = Long.parseLong(st.nextToken());
                    union(a, b, w);
                } else {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    query(a, b);
                }
            }
        }
        System.out.print(sb);
    }

    static void query(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA != parentB) {
            sb.append("UNKNOWN").append("\n");
        } else {
            sb.append(weightDiff[a] - weightDiff[b]).append("\n");
        }
    }

    static void union(int a, int b, long w) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return;
        weightDiff[parentA] = weightDiff[b] - weightDiff[a] + w;
        parent[parentA] = parentB;
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x ;
        }

        int p = find(parent[x]);
        weightDiff[x] += weightDiff[parent[x]];
        parent[x] = p;
        return parent[x];
    }
}
