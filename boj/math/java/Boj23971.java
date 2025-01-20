import java.io.*;
import java.util.*;

public class Boj23971 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int h,w,n,m;

        h = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        n++; m++;
        int a = w / m;
        int b = h / n;
        if (w % m != 0) {
            a++;
        }
        if (h % n != 0) {
            b++;
        }

        System.out.println(a * b);
    }
}
