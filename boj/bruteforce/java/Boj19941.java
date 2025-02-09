import java.io.*;
import java.util.*;

public class Boj19941 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        String input = br.readLine();
        HashSet<Integer> eatenHamburger = new HashSet<>();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (input.charAt(i) == 'H') continue;

            for (int j = Math.max(0, i - k); j <= Math.min(n - 1, i + k); j++) {
                if (input.charAt(j) == 'P' || eatenHamburger.contains(j)) continue;
                answer++;
                eatenHamburger.add(j);
                break;
            }
        }

        System.out.println(answer);
    }
}
