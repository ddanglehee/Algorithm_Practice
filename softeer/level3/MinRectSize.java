import java.util.*;
import java.io.*;


public class MinRectSize
{

    static int answer = 4000000;
    static int n;
    static int k;
    static ArrayList[] pointOfColor;

    public static void main(String args[]) throws IOException
    {
        init();

        ArrayList<int[]> color1List = pointOfColor[1];
        for (int i = 0; i < color1List.size(); i++) {
            int[] point = color1List.get(i);
            dfs(point[0], point[1], point[0], point[1], 1);
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        pointOfColor = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            pointOfColor[i] = new ArrayList<int[]>();
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            pointOfColor[k].add(new int[] {x, y});
        }
    }

    private static void dfs(int x1, int y1, int x2, int y2, int color) {
        if (color == k) {
            int rectSize = (x2 - x1) * (y2 - y1);
            if (rectSize < answer) answer = rectSize;
            return;
        }

        ArrayList<int[]> nextColorList = pointOfColor[color + 1];
        for (int i = 0; i < nextColorList.size(); i++) {
            int[] point = nextColorList.get(i);
            int x = point[0]; int y = point[1];

            int newX1 = Math.min(x1, x); int newY1 = Math.min(y1, y);
            int newX2 = Math.max(x2, x); int newY2 = Math.max(y2, y);

            if ((newX2 - newX1) * (newY2 - newY1) < answer) {
                dfs(newX1, newY1, newX2, newY2, color + 1);
            }
        }
    }
}
