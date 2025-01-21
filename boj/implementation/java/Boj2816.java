import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2816 {

    static int current = 0;
    static int n;
    static String[] channels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        channels = new String[n];

        StringBuilder sb = new StringBuilder();

        int kbs1Index = 0, kbs2Index = 0;
        for (int i = 0; i < n; i++) {
            String channel = br.readLine();
            channels[i] = channel;

            if (channel.equals("KBS1")) {
                kbs1Index = i;
            }
            if (channel.equals("KBS2")) {
                kbs2Index = i;
            }
        }

        if (kbs2Index < kbs1Index) kbs2Index++;

        for (int i = 0; i < kbs1Index; i++) {
            sb.append(1);
        }
        for (int i = 0; i < kbs1Index; i++) {
            sb.append(4);
        }
        for (int i = 0; i < kbs2Index; i++) {
            sb.append(1);
        }
        for (int i = 1; i < kbs2Index; i++) {
            sb.append(4);
        }

        System.out.print(sb);
    }
}
