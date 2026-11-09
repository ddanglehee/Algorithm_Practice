import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Map<Integer, Integer> map = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        while(n-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int k = Integer.parseInt(st.nextToken());
            if (cmd.equals("add")) {
                int v = Integer.parseInt(st.nextToken());

                map.put(k, v);
            } else if (cmd.equals("remove")) {
                map.remove(k);
            } else {
                sb.append(map.get(k) == null ? "None" : map.get(k)).append("\n");
            }
        }
        System.out.print(sb);
    }
}