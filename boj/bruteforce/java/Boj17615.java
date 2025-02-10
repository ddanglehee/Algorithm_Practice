import java.io.*;

public class Boj17615 {

    static int n;
    static String input;
    static int answer = 500000;
    static int result;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new char[n];
        input = br.readLine();

        // 1. 파란색볼 왼쪽 / 파란색 볼 옮기기
        init();
        for (int i = 1; i < n; i++) {
            if (arr[i] == 'B' && arr[i - 1] == 'R') {
                result++;
                arr[i] = 'R';
            }
        }
        answer = Math.min(answer, result);

        // 2. 파란색볼 왼쪽 / 빨간색 볼 옮기기
        init();
        for (int i = n - 2; 0 <= i; i--) {
            if (arr[i] == 'R' && arr[i + 1] == 'B') {
                result++;
                arr[i] = 'B';
            }
        }
        answer = Math.min(answer, result);

        // 3. 파란색볼 오른쪽 / 파란색 볼 옮기기
        init();
        for (int i = n - 2; 0 <= i; i--) {
            if (arr[i] == 'B' && arr[i + 1] == 'R') {
                result++;
                arr[i] = 'R';
            }
        }
        answer = Math.min(answer, result);

        // 4. 파란색볼 오른쪽 / 빨간색 볼 옮기기
        init();
        for (int i = 1; i < n; i++) {
            if (arr[i] == 'R' && arr[i - 1] == 'B') {
                result++;
                arr[i] = 'B';
            }
        }
        answer = Math.min(answer, result);

        System.out.println(answer);
    }

    public static void init() {
        for (int i = 0; i < n; i++) {
            arr[i] = input.charAt(i);
        }
        result = 0;
    }
}
