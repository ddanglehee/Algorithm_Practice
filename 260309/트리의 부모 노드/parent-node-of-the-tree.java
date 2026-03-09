import java.util.*;
import java.io.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) - 1;

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            sb.append(st.nextToken()).append("\n");
        }

        System.out.print(sb);
    }
}