import java.util.*;
import java.io.*;

public class Boj15661 {

    static int n;
    static int[][] s;
    static int answer = 40000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        s = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                s[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, new ArrayList<>(), new ArrayList<>());

        System.out.println(answer);
    }

    private static void solve(int player, ArrayList<Integer> startTeam, ArrayList<Integer> linkTeam) {
        if (player == n) {
            int startSum = getTotalStatistics(startTeam);
            int linkSum = getTotalStatistics(linkTeam);
            answer = Math.min(answer, Math.abs(startSum - linkSum));
            return;
        }

        startTeam.add(player);
        solve(player + 1, startTeam, linkTeam);
        startTeam.remove(startTeam.size() - 1);
        linkTeam.add(player);
        solve(player + 1, startTeam, linkTeam);
        linkTeam.remove(linkTeam.size() - 1);
    }

    static private int getTotalStatistics(ArrayList<Integer> team) {
        int total = 0;

        for (int i = 0; i < team.size(); i++) {
            for (int j = i + 1; j < team.size(); j++) {
                int iPlayer = team.get(i);
                int jPlayer = team.get(j);
                total += (s[iPlayer][jPlayer] + s[jPlayer][iPlayer]);
            }
        }

        return total;
    }
}
