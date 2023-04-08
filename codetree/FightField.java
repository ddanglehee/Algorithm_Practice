import java.util.*;
import java.io.*;

public class Main {

    static int n, m, k;
    static StringBuilder sb = new StringBuilder();
    static Map<Location, PriorityQueue<Integer>> gunGraph = new HashMap<>();
    static Player[][] playerInGraph;
    static Player[] players;
    static int[] point;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        for (int r = 1; r <= k; r++) {
            round();
        }

        for (int i = 1; i <= m; i++) {
            sb.append(point[i]).append(" ");
        }
        System.out.print(sb);
    }

    static void round() {
        for (int i = 1; i <= m; i++) {
            Player player = players[i];

            // i번 플레이어 본인이 향한 방향대로 한 칸 이동 or 격자 벗어나면 반대 방향으로 한 칸 이동
            int nR = player.location.r + dr[player.d];
            int nC = player.location.c + dc[player.d];
            if (!isInGraph(nR, nC)) {
                player.d = (player.d + 2) % 4;
                nR = player.location.r + dr[player.d];
                nC = player.location.c + dc[player.d];
            }
            playerInGraph[player.location.r][player.location.c] = null;
            player.location.r = nR;
            player.location.c = nC;

            PriorityQueue<Integer> gunQueue = gunGraph.get(player.location);
            if (playerInGraph[nR][nC] == null) {
                // 이동한 칸에 총이 있으면
                if (!gunQueue.isEmpty()) {
                    int gunP1 = player.gunP;
                    int gunP2 = gunQueue.peek();

                    // 그 칸에 있는 총이 i번 플레이어가 가지고 있는 총보다 공격력이 더 쎄면
                    if (gunP1 < gunP2) {
                        if (gunP1 != 0) gunQueue.offer(gunP1);
                        player.gunP = gunP2;
                        gunQueue.poll();
                    }
                }
                playerInGraph[nR][nC] = player;
            } else {
                Player player2 = playerInGraph[nR][nC];
                int p1 = player.p + player.gunP;
                int p2 = player2.p + player2.gunP;

                Player winner = (p1 < p2 || (p1 == p2 && player.p < player2.p)) ? player2 : player;
                Player loser = (p1 < p2 || (p1 == p2 && player.p < player2.p)) ? player : player2;

                // 1. 이긴 플레이어가 (초기 + 총) 차이만큼 포인트 획득
                point[winner.number] += Math.abs(p1 - p2);

                // 2. 진 플레이어가 총을 그 격자에 내려놓고
                if (loser.gunP != 0) gunQueue.offer(loser.gunP);
                loser.gunP = 0;

                // 방향대로 한 칸 이동
                int loserNR = nR + dr[loser.d];
                int loserNC = nC + dc[loser.d];
                // 격자범위 밖이거나 플레이어 있으면 오른쪽으로 90도씩 회전
                if (!isInGraph(loserNR, loserNC) || playerInGraph[loserNR][loserNC] != null) {
                    for (int d = 1; d <= 4; d++) {
                        int newD = (loser.d + d) % 4;
                        loserNR = nR + dr[newD];
                        loserNC = nC + dc[newD];
                        if (isInGraph(loserNR, loserNC) && playerInGraph[loserNR][loserNC] == null) {
                            loser.d = newD;
                            break;
                        }
                    }
                }
                loser.location.r = loserNR;
                loser.location.c = loserNC;
                playerInGraph[loserNR][loserNC] = loser;
                // 총이 있으면 가장 공격력 높은 거 획득
                if (!gunGraph.get(loser.location).isEmpty()) {
                    loser.gunP = gunGraph.get(loser.location).poll();
                }


                playerInGraph[nR][nC] = winner;
                // 3. 이긴 플레이어 총 처리
                if (!gunGraph.get(winner.location).isEmpty()) {
                    int gunP = gunGraph.get(winner.location).peek();

                    if (winner.gunP < gunP) {
                        if (winner.gunP != 0) gunQueue.offer(winner.gunP);
                        winner.gunP = gunP;
                        gunQueue.poll();
                    }
                }
            }
        }
    }

    static boolean isInGraph(int r, int c) {
        return 0 < r && r <= n && 0 < c && c <= n;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        playerInGraph = new Player[n+1][n+1];
        players = new Player[m+1];
        point = new int[m+1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int power = Integer.parseInt(st.nextToken());
                PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
                if (power != 0) queue.offer(power);
                gunGraph.put(new Location(i, j), queue);
            }
        }


        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());

            Player player = new Player(i, new Location(r, c), d, p);
            playerInGraph[r][c] = player;
            players[i] = player;
        }
    }
}

class Location {
    int r;
    int c;

    public Location(int r, int c) {
        this.r = r;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        Location location = (Location) o;
        return this.r == location.r && this.c == location.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, c);
    }
}

class Player {
    int number;
    Location location;
    int d;
    int p;
    int gunP = 0;

    public Player(int number, Location location, int d, int p) {
        this.number = number;
        this.location = location;
        this.d = d;
        this.p = p;
    }
}