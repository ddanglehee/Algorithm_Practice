import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.io.*;


class Solution
{
    private static int[] array;
    private static int answer;
    private static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        for (int test_case = 1; test_case <= T; test_case++) {
            answer = 0;
            st = new StringTokenizer(br.readLine());

            // Array에 숫자판의 정보 넣기
            String str = st.nextToken();
            array = new int[str.length()];
            for (int i = 0; i < str.length(); i++) {
                array[i] = str.charAt(i) - '0';
            }

            // 교환 횟수
            int count = Integer.parseInt(st.nextToken());

            // 번호판의 최댓값 찾기
            int[] maxArray = Arrays.copyOf(array, array.length);
            Arrays.sort(maxArray);
            for (int i = 0; i < maxArray.length / 2; i++) {
                switchArray(maxArray, i, maxArray.length - i - 1);
            }
            max = getIntValue(maxArray);

            switch2Numbers(str.length(), count, false);

            sb.append('#').append(test_case).append(' ').append(answer).append("\n");
        }

        System.out.print(sb);
    }

    private static boolean switch2Numbers(int l, int remainCount, boolean findMax) {

        if (remainCount == 0) {
            answer = Math.max(answer, getIntValue(array));
            return false;
        }

        for (int i = 0; i < l; i++) {
            for (int j = i + 1; j < l; j++) {
                switchArray(array, i, j);

                if (getIntValue(array) == max) {
                    if ((remainCount - 1) % 2 == 0) {
                        answer = max;
                    } else {
                        switch2Numbers(l, 1, true);
                    }
                    return true;
                }

                if(switch2Numbers(l, remainCount - 1, false)) return true;
                switchArray(array, j, i);
            }
        }

        return findMax;
    }

    private static void switchArray(int[] target, int i, int j) {
        int tmp = target[i];
        target[i] = target[j];
        target[j] = tmp;
    }

    private static int getIntValue(int[] arr) {
        int result = Integer.parseInt(Arrays.stream(arr)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining())
        );
        return result;
    }

}