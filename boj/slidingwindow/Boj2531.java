import java.util.*;
import java.io.*;

public class Boj2531 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        HashSet<Integer> hs = new HashSet<>();
        int[] count = new int[d + 1];
        int s = 0;
        int e = k;
        for (int i = s; i < e; i++) {
            hs.add(sushi[i]);
            count[sushi[i]]++;
        }
        hs.add(c);
        int answer = hs.size();
        while (s < n) {
            count[sushi[s]]--;
            if (count[sushi[s]] == 0) {
                hs.remove(sushi[s]);
            }
            s++;
            hs.add(sushi[e]);
            count[sushi[e++]]++;
            hs.add(c);
            e %= n;

            answer = Math.max(answer, hs.size());
        }

        System.out.println(answer);
    }
}
