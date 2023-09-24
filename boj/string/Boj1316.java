import java.io.*;

public class Boj1316 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            boolean flag = true;
            boolean[] isUsed = new boolean[26];

            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);

                if (isUsed[c - 'a'] && word.charAt(j - 1) != c) {
                    flag = false;
                    break;
                }
                isUsed[c - 'a'] = true;
            }

            if (flag) answer++;
        }

        System.out.print(answer);
    }
}
