import java.io.*;
import java.util.*;

public class Boj2866 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        br.readLine(); // 한 줄 버리기
        HashSet<String> stringSet = new HashSet<>();
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

        int start = -1;
        boolean flag = true;
        while (flag) {
            start++;
            for (int j = 0; j < c; j++) {
                String string = stringArray[j].substring(start);
                if (stringSet.contains(string)) {
                    flag = false;
                    break;
                }
                stringSet.add(string);
            }
            stringSet.clear();
        }

        System.out.print(start);
    }
}
