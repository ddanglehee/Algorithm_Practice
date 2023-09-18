import java.util.*;
import java.io.*;

public class Boj5567 {

    static HashSet<Integer> invited = new HashSet<>();
    static ArrayList<Integer>[] friend;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        friend = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            friend[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friend[a].add(b);
            friend[b].add(a);
        }

        dfs(1, 0);

        System.out.print(invited.size() - 1);
    }

    static void dfs(int cur, int depth) {
        invited.add(cur);

        if (depth == 2) return;

        for (int f: friend[cur]) {
            dfs(f, depth + 1);
        }
    }
}
