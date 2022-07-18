import java.io.*;
import java.util.*;

public class Boj1759 {

    static StringBuilder sb = new StringBuilder();
    static int L, C;
    static ArrayList<Character> cipherList = new ArrayList<>();
    static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
    static int consonantCount = 0;
    static int vowelCount = 0;
    static int cipherLength = 0;
    static String tmpString = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st1.nextToken());
        C = Integer.parseInt(st1.nextToken());

        StringTokenizer st2 = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            cipherList.add(st2.nextToken().charAt(0));
        }
        Collections.sort(cipherList);

        for (int i = 0; i < C - L + 1; i++) {
            dfs(i);
        }

        System.out.println(sb);
    }

    static void dfs(int index) {
        // 1. 체크인
        tmpString += Character.toString(cipherList.get(index));
        if (isVowel(cipherList.get(index))) vowelCount++; else consonantCount++;

        // 2. 목적지인가?
        if (tmpString.length() == L && 2 <= consonantCount && 1 <= vowelCount) {
            sb.append(tmpString).append("\n");
        } else {
            // 3. 연결된 곳 순회
            for (int i = index + 1; i < C; i++) {
                // 4. 갈 수 있는가?, 5. 간다.
                dfs(i);
            }
        }

        // 6. 체크아웃

        tmpString = tmpString.substring(0, tmpString.length()-1);
        if (isVowel(cipherList.get(index))) vowelCount--; else consonantCount--;
    }

    static boolean isVowel(char c) {
        for (char vowel : vowels) {
            if (c == vowel) return true;
        }
        return false;
    }
}
