import java.io.*;
import java.util.*;

public class Boj8979 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int kGold = 0, kSilver = 0, kBronze = 0;
        int[][] country = new int[n+1][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            country[id][0] = Integer.parseInt(st.nextToken());
            country[id][1] = Integer.parseInt(st.nextToken());
            country[id][2] = Integer.parseInt(st.nextToken());

            if (id == k) {
                kGold = country[id][0];
                kSilver = country[id][1];
                kBronze = country[id][2];
            }
        }

        int rank = 1;
        for (int i = 1; i < n+1; i++) {
            if (kGold == country[i][0]) {
                if (kSilver == country[i][1]) {
                    if (kBronze < country[i][2]) rank++;
                } else if (kSilver < country[i][1]) rank++;
            } else if (kGold < country[i][0]) rank++;
        }

        System.out.println(rank);
    }
}
