import java.util.*;
import java.io.*;

class ChickenDelivery {

    static int answer = 1300;
    static int n, m;
    static int chickenCount;
    static boolean s = true; // combination 최적화하기 위해서 (nCk에서 (n / 2 < k)인 경우 nCn-k로 계산)
    static ArrayList<int[]> chickenList = new ArrayList<>();
    static ArrayList<int[]> houseList = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> chickenCombinationList = new ArrayList<>();
    static boolean[] isSelected;
    static int[] chickenDistance;

    public static void main(String[] args) throws IOException {
        init();

        combination(m, 0, 0);

        for (ArrayList<Integer> currentCombination : chickenCombinationList) {
            for (int a = 0; a < chickenDistance.length; a++) {
                chickenDistance[a] = 100;
            }

            for (Integer integer : currentCombination) {
                int[] currentChicken = chickenList.get(integer);
                for (int k = 0; k < houseList.size(); k++) {
                    int[] currentHouse = houseList.get(k);
                    int currentChickenDistance = Math.abs(currentChicken[0] - currentHouse[0]) + Math.abs(currentChicken[1] - currentHouse[1]);
                    chickenDistance[k] = Math.min(chickenDistance[k], currentChickenDistance);
                }
            }

            int cityChickenDistance = 0;
            for (int i : chickenDistance) {
                cityChickenDistance += i;
            }
            answer = Math.min(answer, cityChickenDistance);
        }

        System.out.print(answer);
    }

    private static void combination(int k, int count, int index) {
        if (count == k) {
            saveCombination();
            return;
        }

        for (int i = index; i < chickenCount; i++) {
            if (isSelected[i] != s) {
                isSelected[i] = s;
                combination(k, count + 1, index + 1);
                isSelected[i] = !s;
            }
        }
    }

    private static void saveCombination() {
        ArrayList<Integer> selectedList = new ArrayList<>();
        for (int i = 0; i < chickenCount; i++) {
            if (isSelected[i]) {
                selectedList.add(i);
            }
        }
        chickenCombinationList.add(selectedList);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) {
                    houseList.add(new int[] {i, j});
                } else if (input == 2) {
                    chickenList.add(new int[] {i, j});
                }
            }
        }
        chickenCount = chickenList.size();
        chickenDistance = new int[houseList.size()];
        isSelected = new boolean[chickenCount];
        if (chickenCount / 2 < m) {
            s = false;
            for (int i = 0; i < chickenCount; i++) {
                isSelected[i] = true;
            }
            m = chickenCount - m;
        }
    }
}
