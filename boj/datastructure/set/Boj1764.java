import java.io.*;
import java.util.*;

public class Boj1764 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(br.readLine());
        }

        HashSet<String> answerSet = new HashSet<>();
        for (int j = 0; j < m; j++) {
            String input = br.readLine();
            if (set.contains(input)) answerSet.add(input);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(answerSet.size()).append("\n");

        ArrayList<String> answerList = new ArrayList<>(answerSet);
        Collections.sort(answerList);

        for (String name: answerList) {
            sb.append(name).append("\n");
        }

        System.out.print(sb);
    }
}
