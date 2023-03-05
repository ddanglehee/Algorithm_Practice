import java.util.*;
import java.io.*;

// 정답 풀이
public class ValidateBusOrder
{
    private static Long answer = 0L;
    private static int n;
    private static int[] busNumbers;
    private static int[][] arr; // arr[x][j] : busNumbers의 j번째보다 이후에 있는 것들 중에 x보다 작은 것들의 수

    public static void main(String args[]) throws IOException
    {
        init();

        for (int x = 1; x <= n; x++) {
            for (int j = n - 1; 0 < j; j--) {
                if (busNumbers[j + 1] < x) {
                    arr[x][j] = arr[x][j + 1] + 1;
                } else {
                    arr[x][j] = arr[x][j + 1];
                }
            }
        }

        for (int i = 1; i <= n - 2; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                // ai < aj일 때 ak < ai인 경우의 수를 answer에 더함
                // busNumbers에서 j번째보다 이후에 있는 버스 번호 중 busNumbers[i]보다 작은 것들의 개수를 answer에 더함
                if (busNumbers[i] < busNumbers[j]) {
                    answer += arr[busNumbers[i]][j];
                }
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n + 1][n + 1];
        busNumbers = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int busNumber = Integer.parseInt(st.nextToken());
            busNumbers[i] = busNumber;
        }
    }
}

//----------------------------------------------------------------------------------------------
// 결과는 같지만, 시간 초과 풀이
public class ValidateBusOrder2
{
    private static Long answer = 0L;
    private static int n;
    private static ArrayList<int[]> combinations = new ArrayList<>();
    private static ArrayList<Integer> tmpCombinations = new ArrayList<>();
    private static int[] busNumbers;
    private static boolean[] visited;

    public static void main(String args[]) throws IOException
    {
        init();
        combination(0);

        for (int i = 0; i < combinations.size(); i++) {
            if (!isValidSequence(i)) {
                answer++;
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        busNumbers = new int[n + 1];
        visited = new boolean[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int busNumber = Integer.parseInt(st.nextToken());
            busNumbers[i] = busNumber;
        }
    }

    private static void combination(int start) {
        if (tmpCombinations.size() == 3) {
            combinations.add(new int[]{tmpCombinations.get(0), tmpCombinations.get(1), tmpCombinations.get(2)});
            return;
        }

        for (int i = start + 1; i <= n; i++) {
            tmpCombinations.add(i);
            combination(i);
            tmpCombinations.remove(tmpCombinations.size() - 1);
        }
    }

    private static boolean isValidSequence(int index) {
        int[] ijk = combinations.get(index);
        int i = ijk[0]; int j = ijk[1]; int k = ijk[2];

        if (busNumbers[i] < busNumbers[j] && busNumbers[i] > busNumbers[k]) {
            return false;
        } else {
            return true;
        }
    }
}
