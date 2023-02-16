import java.util.*;
import java.io.*;


public class GBC2
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int answer = 0;
    static ArrayList<int[]> limitedSpeed = new ArrayList<>();
    static ArrayList<int[]> testedSpeed = new ArrayList<>();

    public static void main(String args[]) throws IOException
    {

        init();
        calculateMaxDiff();
        System.out.println(answer);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int section = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            limitedSpeed.add(new int[] {section, speed});
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int section = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            testedSpeed.add(new int[] {section, speed});
        }
    }

    private static void calculateMaxDiff() {
        while (!testedSpeed.isEmpty() && !limitedSpeed.isEmpty()) {
            int sectionDiff = limitedSpeed.get(0)[0] - testedSpeed.get(0)[0];
            int speedDiff = testedSpeed.get(0)[1] - limitedSpeed.get(0)[1]; // 이 값이 양수면 테스트에서 제한 속도를 초과함

            if (0 < speedDiff) answer = Math.max(answer, speedDiff);
            // 1. 제한 속도 지정 구간과 테스트 구간이 같으면
            if (sectionDiff == 0) {
                limitedSpeed.remove(0);
                testedSpeed.remove(0);
            }
            // 2. 테스트 구간이 더 길어서, 다음 제한 속도 지정 구간도 확인해야 하는 경우
            else if (sectionDiff < 0) {
                limitedSpeed.remove(0);
                testedSpeed.get(0)[0] = -sectionDiff;
            }
            // 3. 제한 속도 지정 구간이 더 길어서, 다음 테스트 구간과 비교해야 하는 경우
            else {
                testedSpeed.remove(0);
                limitedSpeed.get(0)[0] = sectionDiff;
            }
        }
    }
}