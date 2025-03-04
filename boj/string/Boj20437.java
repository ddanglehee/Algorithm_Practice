import java.io.*;
import java.util.*;

public class Boj20437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer>[] indexOf;
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            }

            indexOf = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                indexOf[i] = new ArrayList<>();
            }

            for (int i = 0; i < str.length(); i++) {
                int c = str.charAt(i) - 'a';
                indexOf[c].add(i);
            }

            int answer3 = 10000;
            int answer4 = 0;
            for (int i = 0; i < 26; i++) {
                if (indexOf[i].size() < 2) continue;

                int j = 0;
                while (j + k - 1 < indexOf[i].size()) {
                    int length = indexOf[i].get(j + k - 1) - indexOf[i].get(j) + 1;
                    answer3 = Math.min(answer3, length);
                    answer4 = Math.max(answer4, length);
                    j++;
                }
            }

            if (answer4 == 0) {
                sb.append(-1).append("\n");
            } else {
                sb.append(answer3).append(" ").append(answer4).append("\n");
            }
        }
        System.out.print(sb);
    }
}
