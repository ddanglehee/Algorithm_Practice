import java.util.*;
import java.io.*;

public class Boj10431 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int p = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] studentHeight = new int[20];
        int answer;
        ArrayList<Integer> line = new ArrayList<>();
        for (int t = 1; t <= p; t++) {
            // 테스트케이스 초기화
            line.clear();
            answer = 0;
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            for (int i = 0; i < 20; i++) {
                studentHeight[i] = Integer.parseInt(st.nextToken());
            }

            // 실제 풀이
            line.add(studentHeight[0]);
            for (int i = 1; i < 20; i++) {
                for (int j = 0; j < line.size(); j++) {
                    if (studentHeight[i] > line.get(j)) continue;

                    answer += line.size() - j;
                    line.add(j, studentHeight[i]);
                    break;
                }

                if (line.size() - 1 != i)  line.add(studentHeight[i]);
            }
            sb.append(t).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
