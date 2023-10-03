import java.io.*;
import java.util.*;

public class Boj18427 {

    static int n, m, h;
    static ArrayList<Integer>[] blocks;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        blocks = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            blocks[i].add(0);
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(blocks[i]);
        }

        for (int i = 0; i < blocks[0].size(); i++) {
            dfs(0, blocks[0].get(i));
        }

        System.out.print(answer);
    }

    private static void dfs(int student, int totalHeight) {
        if (student == n - 1) return;
        for (int j = 0; j < blocks[student + 1].size(); j++) {
            int tmpHeight = totalHeight + blocks[student + 1].get(j);
            if (h <= tmpHeight) {
                if (h == tmpHeight) {
                    answer = (answer + 1) % 10007;
                }
                break;
            }
            dfs(student + 1, tmpHeight);
        }

    }
}
