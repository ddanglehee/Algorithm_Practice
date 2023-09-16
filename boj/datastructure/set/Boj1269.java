import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj1269 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashSet<Integer> aSet = new HashSet<>();
        for (int i = 0; i < a; i++) {
            aSet.add(Integer.parseInt(st.nextToken()));
        }

        int answer = a + b;
        st = new StringTokenizer(br.readLine());
        HashSet<Integer> bSet = new HashSet<>();
        for (int i = 0; i < b; i++) {
            int num = Integer.parseInt(st.nextToken());
            bSet.add(num);
            if (aSet.contains(num)) answer--;
        }

        for (int aa: aSet) {
            if (bSet.contains(aa)) answer--;
        }

        System.out.print(answer);
    }
}
