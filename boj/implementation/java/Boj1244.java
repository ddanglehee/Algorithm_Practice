import java.util.*;
import java.io.*;

public class Boj1244 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] switches = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int sex = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            if (sex == 1) {
                for (int i = number; i <= n; i += number) {
                    switches[i] = switches[i] == 0 ? 1 : 0;
                }
            } else {
                switches[number] = switches[number] == 0 ? 1 : 0;
                for (int i = 1; i <= n / 2; i++) {
                    if (n < number + i || number - i < 1) break;
                    if (switches[number + i] == switches[number - i]) {
                        switches[number + i] = switches[number + i] == 0 ? 1 : 0;
                        switches[number - i] = switches[number - i] == 0 ? 1 : 0;
                    } else {
                        break;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(switches[i]);
            if (i % 20 == 0) sb.append("\n"); else sb.append(" ");
        }

        System.out.print(sb);
    }
}
