import java.util.*;
import java.io.*;

public class Boj20291 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String file = br.readLine();
            for (int j = file.length() - 1; 0 <= j; j--) {
                char c = file.charAt(j);

                if (c == '.') {
                    String ext = file.substring(j + 1);

                    map.put(ext, map.getOrDefault(ext, 0) + 1);
                    break;
                }
            }
        }

        String[] extArray = new String[map.keySet().size()];
        int i = 0;
        for (String ext: map.keySet()) {
            extArray[i++] = ext;
        }
        Arrays.sort(extArray);

        StringBuilder sb = new StringBuilder();
        for (String ext: extArray) {
            sb.append(ext).append(" ").append(map.get(ext)).append("\n");
        }

        System.out.print(sb);
    }
}
