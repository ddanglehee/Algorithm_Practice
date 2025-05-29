import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder sb = new StringBuilder();

        String[] stringNumbers = new String[numbers.length];
        for (int i = 0; i < stringNumbers.length; i++) {
            stringNumbers[i] = Integer.toString(numbers[i]);
        }

        Arrays.sort(stringNumbers, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.parseInt(o2+o1) - Integer.parseInt(o1+o2);
            }
        });

        for (String number : stringNumbers) {
            sb.append(number);
        }

        String answer = sb.toString();
        if (answer.charAt(0) == '0') answer = "0";

        return answer;
    }
}