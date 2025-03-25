import java.util.*;
import java.io.*;

public class Boj1205 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.print(1);
            return;
        }

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        if (n == p && list.get(n - 1) >= t) {
            System.out.print(-1);
        } else {
            int curRank = n + 1;
            for (int i = 0; i < n; i++) {
                if (list.get(i) <= t) {
                    curRank = i + 1;
                    break;
                }
            }
            System.out.println(curRank);
        }
    }
}
