import java.io.*;
import java.util.*;

public class Boj1283 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        boolean[] isUsed = new boolean[26];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int wordCount = st.countTokens();
            String[] words = new String[wordCount];

            for (int j = 0; j < wordCount; j++) {
                words[j] = st.nextToken();
            }

            int keySequence = -1; // 단축키가 포함된 단어의 순서
            int keyIndex = -1; // 해당 단어에서 단축키가 몇번째 인덱스인지

            // 1. 첫글자가 단축키가 될 수 있는지 확인
            for (int j = 0; j < wordCount; j++) {
                char firstChar = words[j].toLowerCase().charAt(0);
                if (!isUsed[firstChar - 'a']) {
                    keySequence = j;
                    keyIndex = 0;
                    isUsed[firstChar - 'a'] = true;
                    break;
                }
            }

            // 2. 첫글자가 단축키가 될 수 없는 경우 첫글자가 아닌 것들도 모두 확인
            for (int j = 0; j < wordCount; j++) {
                if (keySequence != -1) break;
                String word = words[j].toLowerCase();
                for (int k = 0; k < word.length(); k++) {
                    if (!isUsed[word.charAt(k)- 'a']) {
                        keySequence = j;
                        keyIndex = k;
                        isUsed[word.charAt(k)- 'a'] = true;
                        break;
                    }
                }
            }

            // 3. 단축키 선정된 것 표시해주기
            for (int j = 0; j < wordCount; j++) {
                String word = words[j];

                if (keySequence == j) {
                    for (int k = 0; k < word.length(); k++) {
                        if (keyIndex == k) {
                            sb.append('[').append(word.charAt(k)).append(']');
                        } else {
                            sb.append(word.charAt(k));
                        }
                    }
                } else {
                    sb.append(words[j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
