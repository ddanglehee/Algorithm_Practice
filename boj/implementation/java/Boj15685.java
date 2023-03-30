import java.util.*;
import java.io.*;

class Boj15685 {

    static int answer = 0;
    static int n;
    static int x; static int y;
    static int d; static int g;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] isInDragonCurve = new boolean[101][101]; // 드래곤 커브에 있는 꼭짓점이면 true
    static ArrayList<int[]> pointList = new ArrayList<int[]>(); // 하나의 드래곤 커브에서의 꼭짓점 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());
            pointList.clear();
            dragonCurve();
        }
        countSquareInDragonCurve();
        System.out.print(answer);
    }

    static void countSquareInDragonCurve() {
        for (int i = 0; i <= 99; i++) {
            for (int j = 0; j <= 99; j++) {
                if (isInDragonCurve[i][j]
                        && isInDragonCurve[i + 1][j]
                        && isInDragonCurve[i + 1][j + 1]
                        && isInDragonCurve[i][j + 1]) answer++;
            }
        }
    }

    static void dragonCurve() {
        // 0세대 구성
        pointList.add(new int[] {x, y});
        pointList.add(new int[] {x + dx[d], y + dy[d]});

        // g세대까지 드래곤 커브 구성
        for (int generation = 1; generation <= g; generation++) {
            int[] axis = pointList.get(pointList.size() - 1);
            int a = axis[0]; int b = axis[1];

            for (int i = pointList.size() - 2; 0 <= i; i--) {
                int[] point = pointList.get(i);
                pointList.add(new int[] {a + b - point[1], b + point[0] - a});
            }
        }

        for (int[] point: pointList) {
            isInDragonCurve[point[0]][point[1]] = true;
        }
    }
}