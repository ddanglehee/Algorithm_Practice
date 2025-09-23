import java.util.*;
import java.io.*;

public class QueenAnt {

    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static int Q, N;
    private static ArrayList<Integer> x = new ArrayList<>(); // i번 집의 위치

    public static void main(String[] args) throws IOException {
        buildVillage();

        for (int i = 1; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            if (cmd == 200) {
                buildAntHouse(a);
            } else if (cmd == 300) {
                removeAntHouse(a);
            } else if (cmd == 400) {
                lookAround(a);
            }
//            for (int j = 0; j < x.size(); j++) {
//                System.out.print(x.get(j) + " ");
//            }
//            System.out.println();
        }

        System.out.print(sb);
    }

    private static void lookAround(int r) {
        // *이분탐색*
        int s = 0;
        int e = 1000000000;
        int answer = 0;

        while (s <= e) {
            int m = (s + e) / 2;

            int antCount = 1;
            int curTime = 0;

            for (int i = 1; i < x.size() - 1; i++) {
                if (x.get(i) == -1) continue;

                for (int j = i + 1; j < x.size(); j++) {
                    if (x.get(j) == -1) continue;

                    if (m < curTime + (x.get(j) - x.get(i))) {
                        antCount++;
                        curTime = 0;
                    } else {
                        curTime += (x.get(j) - x.get(i));
                    }
                    break;
                }

                if (r < antCount) break;
            }

            if (r < antCount) {
                s = m + 1;
            } else {
                answer = m;
                e = m - 1;
            }
        }

        sb.append(answer).append("\n");
    }

    private static void removeAntHouse(int q) {
        x.set(q, -1);
    }

    private static void buildAntHouse(int p) {
        N++;
        x.add(p);
    }

    private static void buildVillage() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        Q = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        st.nextToken(); // 100은 버리기
        N = Integer.parseInt(st.nextToken());

        // 여왕집 표시
        x.add(0);
        for (int i = 1; i <= N; i++) {
            int xi = Integer.parseInt(st.nextToken());
            x.add(xi);
        }
    }
}