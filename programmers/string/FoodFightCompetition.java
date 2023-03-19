import java.util.*;

class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < food.length; i++) {
            for (int c = 0; c < food[i] / 2; c++) {
                sb.append(i);
            }
        }
        return sb.toString() + "0" + sb.reverse().toString();
    }
}