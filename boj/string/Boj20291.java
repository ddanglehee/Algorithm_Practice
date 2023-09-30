import java.util.*;
import java.io.*;

public class Boj20291 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> map = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String file = br.readLine();
            int j = file.indexOf('.');

            String ext = file.substring(j + 1);

            map.put(ext, map.getOrDefault(ext, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }

        System.out.print(sb);
    }
}
