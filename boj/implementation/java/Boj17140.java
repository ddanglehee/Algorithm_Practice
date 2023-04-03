import java.util.*;
import java.io.*;

class Boj17140 {

    static int r, c, k;
    static int[] count = new int[101];
    static PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
        if (o1[0] == o2[0]) {
            return o1[1] - o2[1];
        }
        return o1[0] - o2[0];
    });
    static int columnSize = 3;
    static int rowSize = 3;
    static int[][] A = new int[100][100];

    public static void main(String[] args) throws IOException {
        init();

        int time = 0;
        while (true) {
            if (A[r - 1][c - 1] == k) break;

            time++;
            if (100 < time) {
                time = -1;
                break;
            }
            if (rowSize >= columnSize) rCalculate();
            else cCalculate();
        }

        System.out.print(time);
    }

    static void rCalculate() {
        int maxColumnSize = 0;
        for (int i = 0; i < rowSize; i++) {
            initCount();

            // 각각의 수 몇번 나왔는지 계산
            for (int j = 0; j < columnSize; j++) {
                if (A[i][j] == 0) continue;
                count[A[i][j]]++;
            }

            // PriorityQueue에 (개수, 수) 넣기
            for (int n = 1; n <= 100; n++) {
                if (count[n] == 0) continue;
                pq.add(new int[] {count[n], n});
            }

            // 정렬된 결과 배열에 넣기
            ArrayList<Integer> tmpList = new ArrayList<>();
            while (!pq.isEmpty()) {
                int[] info = pq.poll();
                tmpList.add(info[1]); tmpList.add(info[0]);
            }
            for (int j = 0; j < tmpList.size(); j++) {
                if (99 < j) break;
                A[i][j] = tmpList.get(j);
            }
            fillColumnZero(i, tmpList.size());
            maxColumnSize = Math.max(maxColumnSize, Math.min(100, tmpList.size()));
        }

        columnSize = maxColumnSize;
    }

    static void cCalculate() {
        int maxRowSize = 0;
        for (int j = 0; j < columnSize; j++) {
            initCount();

            // 각각의 수 몇번 나왔는지 계산
            for (int i = 0; i < rowSize; i++) {
                if (A[i][j] == 0) continue;
                count[A[i][j]]++;
            }

            // PriorityQueue에 (개수, 수) 넣기
            for (int n = 1; n <= 100; n++) {
                if (count[n] == 0) continue;
                pq.add(new int[] {count[n], n});
            }

            // 정렬된 결과 배열에 넣기
            ArrayList<Integer> tmpList = new ArrayList<>();
            while (!pq.isEmpty()) {
                int[] info = pq.poll();
                tmpList.add(info[1]); tmpList.add(info[0]);
            }

            for (int i = 0; i < tmpList.size(); i++) {
                if (99 < i) break;
                A[i][j] = tmpList.get(i);
            }

            fillRowZero(j, tmpList.size());
            maxRowSize = Math.max(maxRowSize, Math.min(100, tmpList.size()));
        }

        rowSize = maxRowSize;
    }

    static void fillRowZero(int column, int from) {
        for (int j = from; j < 100; j++) {
            A[j][column] = 0;
        }
    }

    static void fillColumnZero(int row, int from) {
        for (int i = from; i < 100; i++) {
            A[row][i] = 0;
        }
    }

    static void initCount() {
        for (int i = 1; i <= 100; i++) {
            count[i] = 0;
        }
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int number = Integer.parseInt(st.nextToken());
                A[i][j] = number;
            }
        }
    }
}
