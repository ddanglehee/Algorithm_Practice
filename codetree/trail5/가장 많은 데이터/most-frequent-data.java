import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        while(N-- > 0) {
            String str = br.readLine();

            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        int answer = 0;
        for (int count : map.values()) {
            answer = Math.max(answer, count);
        }
        System.out.print(answer);
    }
}