import java.util.*;
import java.io.*;

public class Boj1327 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String input = "";
        String answer = "";
        for (int i = 1; i <= n; i++) {
            input += st.nextToken();
            answer += Integer.toString(i);
        }

        ArrayDeque<Info> queue = new ArrayDeque<>();
        HashSet<String> isChecked = new HashSet<>();
        isChecked.add(input);
        queue.add(new Info(input, 0));
        while (!queue.isEmpty()) {
            Info info = queue.removeFirst();

            if (info.str.equals(answer)) {
                System.out.print(info.count);
                return;
            }

            for (int i = 0; i + k <= n; i++) {
                String nStr = "";
                if (i != 0) nStr = info.str.substring(0, i);
                nStr += new StringBuilder(info.str.substring(i, i + k)).reverse().toString();
                if (i + k < n) nStr += info.str.substring(i + k);

                if (!isChecked.contains(nStr)) {
                    isChecked.add(nStr);
                    queue.add(new Info(nStr, info.count + 1));
                }
            }
        }

        System.out.print(-1);
    }

    static class Info {
        String str;
        int count;

        public Info(String str, int count) {
            this.str = str;
            this.count = count;
        }
    }
}
