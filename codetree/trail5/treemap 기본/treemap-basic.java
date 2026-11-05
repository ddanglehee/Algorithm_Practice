import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer> treemap = new TreeMap<>();
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int k = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                treemap.put(k, v);
            } else if (cmd.equals("remove")) {
                int k = Integer.parseInt(st.nextToken());
                treemap.remove(k);
            } else if (cmd.equals("find")) {
                int k = Integer.parseInt(st.nextToken());
                if (treemap.containsKey(k)) {
                    sb.append(treemap.get(k)).append("\n");
                } else {
                    sb.append("None").append("\n");
                }
            } else {
                if (treemap.isEmpty()) {
                    sb.append("None\n");
                } else {
                    for (int k : treemap.keySet()) {
                        sb.append(treemap.get(k)).append(" ");
                    }
                    sb.append("\n");
                }
            }
        }
        System.out.print(sb);
    }
}