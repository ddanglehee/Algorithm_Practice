import java.util.*;
import java.io.*;

public class Boj14889 {

    static int n;
    static int[][] s;
    static int answer = 5000;
    static boolean[] isTeam;

    public static void main(String[] args) throws IOException {
        input();
        solution(1, 0);
        System.out.print(answer);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        s = new int[n + 1][n + 1];
        isTeam = new boolean[n + 1];

        StringTokenizer st;
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int Sij = Integer.parseInt(st.nextToken());
                s[i][j] = Sij;
            }
        }
    }

    private static void solution(int current, int count) {
        if (count == n / 2) {
            int diff = calculateDiff();
            answer = Math.min(answer, diff);
            return;
        }

        for (int i = current; i <= n; i++) {
            if (isTeam[i]) continue;
            isTeam[i] = true;
            solution(i, count + 1);
            isTeam[i] = false;
        }
    }

    private static int calculateDiff() {
        ArrayList<Integer> team1 = new ArrayList<>();
        ArrayList<Integer> team2 = new ArrayList<>();

        for (int i = 1; i <= n; i++){
            if (isTeam[i]) team1.add(i); else team2.add(i);
        }

        int team1Count = 0;
        int team2Count = 0;

       for (int i = 0; i < n / 2 - 1; i++) {
           for (int j = i + 1; j < n / 2; j++) {
               team1Count += s[team1.get(i)][team1.get(j)] + s[team1.get(j)][team1.get(i)];
               team2Count += s[team2.get(i)][team2.get(j)] + s[team2.get(j)][team2.get(i)];
           }
       }

       return Math.abs(team1Count - team2Count);
    }
}
