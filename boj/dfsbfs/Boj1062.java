import java.io.*;
import java.util.*;

public class Boj1062 {

    static int n, k;
    static int result = 0;
    static String[] words;
    static boolean[] visited = new boolean[26];
    static int selectedCount = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];

        if (k < 5) {
            System.out.println(result);
            return;
        }

        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < n; i++) {
            words[i] = br.readLine().replaceAll("[antic]", "");
        }

        result = countWords();

        for (int i = 0; i < 26; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }
        System.out.println(result);
    }

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        selectedCount++;

        // 2. 목적지인가?
        if (selectedCount == k) {
            result = Math.max(result, countWords());
        } else {
            // 3. 연결된 곳을 순회
            for (int i = index + 1; i < 26; i++) {
                // 4. 갈 수 있는가?
                if (!visited[i]) {
                    // 5. 간다.
                    dfs(i);
                }
            }
        }
        // 6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    private static int countWords() {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            boolean isPossible = true;
            for (int j = 0; j < words[i].length(); j++) {
                if (!visited[words[i].charAt(j) - 'a']) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) tmp++;
        }
        return tmp;
    }
}