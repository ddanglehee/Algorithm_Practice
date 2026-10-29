import java.util.*;
import java.io.*;

public class Main {

    private static int n, K;
    private static int[] input;
    private static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        tree = new ArrayList[K];
        for (int i = 0; i < K; i++) {
            tree[i] = new ArrayList<>();
        }

        n = (int)Math.pow(2, K) - 1;
        input = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        print(0, 0, n - 1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            for (int a : tree[i]) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void print(int depth, int s, int e) {
        tree[depth].add(input[(s+e) / 2]);

        if (depth == K - 1) return;
        print(depth + 1, s, (s+e) / 2);
        print(depth + 1, (s+e) / 2 + 1, e);
    }
}