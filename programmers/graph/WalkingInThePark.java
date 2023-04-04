import java.util.*;

class WalkingInThePark {

    int[][] graph; // 0: 갈 수 있음, 1: 장애물
    int r, c;
    int curR, curC;
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0}; // 서 동 북 남
    Map<String, Integer> dMap = new HashMap<>() {{
        put("N", 2);
        put("S", 3);
        put("W", 0);
        put("E", 1);
    }};

    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        initGraph(park);

        StringTokenizer st;
        for (String route: routes) {
            st = new StringTokenizer(route);
            int d = dMap.get(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int nR = curR + dr[d] * l; int nC = curC + dc[d] * l;
            if (!canGo(nR, nC)) continue;

            boolean hasObstacle = false;
            for (int i = 1; i <= l; i++) {
                if (graph[curR + dr[d] * i][curC + dc[d] * i] == 1) {
                    hasObstacle = true;
                    break;
                }
            }

            if (!hasObstacle) {
                curR = nR; curC = nC;
            }
        }

        answer[0] = curR; answer[1] = curC;
        return answer;
    }

    private boolean canGo(int nR, int nC) {
        return 0 <= nR && nR < r && 0 <= nC && nC < c;
    }

    private void initGraph(String[] park) {
        r = park.length;
        c = park[0].length();
        graph = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (park[i].charAt(j) == 'S') {
                    curR = i; curC = j;
                } else if (park[i].charAt(j) == 'X') {
                    graph[i][j] = 1;
                }
            }
        }
    }
}