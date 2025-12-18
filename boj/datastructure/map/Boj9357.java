import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.io.BufferedReader;
import java.util.StringTokenizer;

class Boj9375 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Map<String, ArrayList<String>> map = new HashMap<>();
        while (0 < T--) {
            int n = Integer.parseInt(br.readLine());
            int answer = 1;
            map.clear();

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                String name = st.nextToken();
                String type = st.nextToken();
                if (map.get(type) == null) {
                    map.put(type, new ArrayList<>());
                }
                ArrayList<String> list = map.get(type);
                list.add(name);
            }

            for (String type : map.keySet()) {
                ArrayList<String> list = map.get(type);
                answer *= (list.size() + 1);
            }

            sb.append(answer - 1).append("\n");
        }

        System.out.print(sb);
    }
}
