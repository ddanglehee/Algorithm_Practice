import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int N = Integer.parseInt(st.nextToken());
       int M = Integer.parseInt(st.nextToken());
       Map<String, Integer> map1 = new HashMap<>();
       Map<Integer, String> map2 = new HashMap<>();

       for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            map1.put(str, i);
            map2.put(i, str);
       }

       StringBuilder sb = new StringBuilder();
       while(M-- > 0) {
            String str = br.readLine();
            char c = str.charAt(0);
            if (Character.isDigit(str.charAt(0))) {
                sb.append(map2.get(Integer.parseInt(str)));
            } else {
                sb.append(map1.get(str));
            }
            sb.append("\n");
       }
       System.out.print(sb);
    }
}