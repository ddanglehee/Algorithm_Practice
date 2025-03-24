import java.util.*;
import java.io.*;

public class Boj25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        String game = st.nextToken();
        int gamePlayerCount;
        if (game.equals("Y")) {
            gamePlayerCount = 2;
        } else if (game.equals("F")) {
            gamePlayerCount = 3;
        } else {
            gamePlayerCount = 4;
        }

        Set<String> playedSet = new HashSet<>();
        int count = 1;
        int answer = 0;
        while (n-- > 0) {
            String player = br.readLine();

            if (playedSet.contains(player)) continue;
            playedSet.add(player);
            count++;

            if (count == gamePlayerCount) {
                answer++;
                count = 1;
            }
        }

        System.out.print(answer);
    }
}
