import java.util.*;
import java.io.*;

public class PasswordOfTreasureBox {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, K;
    static int Ndiv4;
    static LinkedList<String> cap = new LinkedList<>();
    static Set<String> hexSet = new HashSet<>();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
           init();
           sb.append("#").append(test_case).append(" ").append(solution()).append("\n");
        }

        System.out.print(sb);
    }

    static int solution() {
        StringBuilder tmpHex = new StringBuilder();

        for (int i = 0; i < Ndiv4; i++) {
            rotateCap();

            for (int j = 0; j < N; j++) {
                tmpHex.append(cap.get(j));
                if (j % Ndiv4 == Ndiv4 - 1) {
                    hexSet.add(tmpHex.toString());
                    tmpHex.setLength(0);
                }
            }
        }

        String[] hexArray = new String[hexSet.size()];
        int i = 0;
        for (String hex: hexSet) {
            hexArray[i++] = hex;
        }
        Arrays.sort(hexArray, Collections.reverseOrder());

        return Integer.parseInt(hexArray[K - 1], 16);
    }

    static void rotateCap() {
        cap.addFirst(cap.removeLast());
    }

    static void init() throws IOException {
        cap.clear(); hexSet.clear();
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); Ndiv4 = N / 4;
        K = Integer.parseInt(st.nextToken());

        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            cap.add(Character.toString(input.charAt(i)));
        }
    }
}
