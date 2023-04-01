import java.util.*;
import java.io.*;

class Boj17837 {

    static int answer;
    static int n, k;
    static int[][] graph;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static ArrayList<int[]>[][] horseInGraph; // graph[i][j]에 있는 말의 정보(번호, 행, 열, 방향) 리스트
    static int[][] horseInfos; // horseInfos[i] : i번 말의 {번호, 행, 열, 방향}
    static boolean canExit = false;

    public static void main(String[] args) throws IOException {
        init();

        int turn = 0;
        while (true) {
            turn++;
            if (1000 < turn) {
                turn = -1;
                break;
            }

            for (int i = 1; i <= k; i++) {
                moveHorse(i);
                if (canExit) break;
            }

            if (canExit) break;
        }

        System.out.print(turn);
    }

    static void moveHorse(int i) {
        int r = horseInfos[i][1]; int c = horseInfos[i][2]; int d = horseInfos[i][3];
        int nextR = r + dr[d];
        int nextC = c + dc[d];

        if (!isInGraph(nextR, nextC) || graph[nextR][nextC] == 2) { // 체스판 벗어나거나 파란색
            horseInfos[i][3] = changeDirection(d);

            nextR = r + dr[horseInfos[i][3]];
            nextC = c + dc[horseInfos[i][3]];
            if (isInGraph(nextR, nextC) && graph[nextR][nextC] != 2) {
                moveHorse(i);
            }
        } else {
            // i번 말 위에있는 애들도 다같이 이동해야 하기 때문에 마지막 index부터 순회
            ArrayList<int[]> tmpList = new ArrayList<>();
            for (int index = horseInGraph[r][c].size() - 1; 0 <= index; index--) {
                int[] horseInfo = horseInGraph[r][c].get(index); // 여기서 안해주면 get연산자 이상하게 동작

                // 빨간색
                if (graph[nextR][nextC] == 1) {
                    tmpList.add(horseInGraph[r][c].remove(index));
                }
                // 흰색
                else {
                    tmpList.add(0, horseInGraph[r][c].remove(index));
                }
                horseInfos[horseInfo[0]][1] = nextR; horseInfos[horseInfo[0]][2] = nextC; // 이동

                // i번말 까지만 이동하고 break;
                if (horseInfo[0] == i) {
                    horseInGraph[nextR][nextC].addAll(tmpList);
                    break;
                }
            }

            if (4 <= horseInGraph[nextR][nextC].size()) canExit = true;
        }
    }

    static int changeDirection(int d) {
        if (d == 0) {
            return 1;
        } else if (d == 1) {
            return 0;
        } else if (d == 2) {
            return 3;
        } else {
            return 2;
        }
    }

    static boolean isInGraph(int r, int c) {
        return 0 < r && r <= n && 0 < c && c <= n;
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        horseInGraph = new ArrayList[n + 1][n + 1];
        horseInfos = new int[k + 1][4];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                horseInGraph[i][j] = new ArrayList<>();
                int color = Integer.parseInt(st.nextToken());
                graph[i][j] = color;
            }
        }

        for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int[] horse = new int[] {i, r, c, d - 1}; // 얕은 복사로 말 정보 변경했을 때 horseInfo, horseInGraph 모두 변경되도록
            horseInfos[i] = horse;
            horseInGraph[r][c].add(horse);
        }
    }
}
