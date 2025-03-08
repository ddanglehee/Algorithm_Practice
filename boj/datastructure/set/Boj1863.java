import java.io.*;
import java.util.*;

public class Boj1863 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        StringTokenizer st;
        int x, y;
        int answer = 0;
        HashSet<Integer> coveredSet = new HashSet<>();
        int max = 0;
        while (n-- > 0) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            if (y < max) {
                for (Iterator<Integer> it = coveredSet.iterator(); it.hasNext(); ) {
                    int h = it.next();
                    if (y < h && h <= max) {
                        it.remove();
                    }
                }

            }
            if (!coveredSet.contains(y) && y != 0) {
                coveredSet.add(y);
                answer++;
            }
            max = y;
        }

        System.out.print(answer);
    }
}
