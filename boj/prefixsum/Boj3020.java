import java.io.*;
import java.util.StringTokenizer;

public class Boj3020 {

    static int n, h;
    static int[] bottom;
    static int[] top;

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 1; i < h; i++) {
            top[i + 1] += top[i];
            bottom[h - i] += bottom[h - i + 1];
        }

        int answer = n;
        int count = 0;
        for (int i = 1; i <= h; i++) {
            if (top[i] + bottom[i] < answer) {
                count = 1;
                answer = top[i] + bottom[i];
            } else if (top[i] + bottom[i] == answer) {
                count++;
            }
        }

        System.out.println(answer + " " + count);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        bottom = new int[h + 1];
        top = new int[h + 1];
        for (int i = 0; i < n; i++) {
            int k = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                bottom[k]++;
            } else {
                top[h + 1 - k]++;
            }
        }
    }
}
