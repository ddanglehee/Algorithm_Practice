import java.util.*;
import java.io.*;

public class Boj1092 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> crane = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int limit = Integer.parseInt(st.nextToken());
            crane.add(limit);
        }
        Collections.sort(crane, Collections.reverseOrder());

        int m = Integer.parseInt(br.readLine());
        ArrayList<Integer> box = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            int weight = Integer.parseInt(st.nextToken());
            box.add(weight);
        }
        Collections.sort(box, Collections.reverseOrder());

        if (crane.get(0) < box.get(0)) {
            System.out.print(-1);
            return;
        }

        int answer = 0;
        while (!box.isEmpty()) {

            int boxIndex = 0;
            for (int craneIndex = 0; craneIndex < n; ) {
                if (boxIndex == box.size()) break;

                if (box.get(boxIndex) <= crane.get(craneIndex)) {
                    box.remove(boxIndex);
                    craneIndex++;
                } else {
                    boxIndex++;
                }
            }

            answer++;
        }

        System.out.print(answer);
    }
}
