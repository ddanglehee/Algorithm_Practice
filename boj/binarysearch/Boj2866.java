import java.io.*;
import java.util.*;

public class Boj2866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        br.readLine(); // 한 줄 버리기
        StringBuilder[] stringArray = new StringBuilder[c];
        for (int j = 0; j < c; j++) {
            stringArray[j] = new StringBuilder();
        }
        for (int i = 1; i < r; i++) {
            String input = br.readLine();
            for (int j = 0; j < c; j++) {
                stringArray[j].append(input.charAt(j));
            }
        }

        int start = 0;
        int end = r - 2;
        int answer = 0;
        while (start <= end) {
            HashSet<String> set = new HashSet<>();
            int mid = (start + end) / 2;

            boolean flag = false; // flag가 false면 중복 안일어남
            for (int j = 0; j < c; j++) {
                String string = stringArray[j].substring(mid);
                if (set.contains(string)) {
                    flag = true;
                    break;
                }
                set.add(string);
            }

            if (flag) {
                end = mid - 1;
            } else {
                start = mid + 1;
                answer = mid + 1;
            }
        }

        System.out.print(answer);
    }
}
