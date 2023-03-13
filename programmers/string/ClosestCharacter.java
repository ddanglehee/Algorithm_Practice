import java.util.*;

class ClosestCharacter {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];
        Map<Character, Integer> indexOfCharMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            int beforeIndex = indexOfCharMap.getOrDefault(currentChar, -1);
            if (beforeIndex == -1) {
                answer[i] = beforeIndex;
            } else {
                answer[i] = i - beforeIndex;
            }
            indexOfCharMap.put(currentChar, i);
        }

        return answer;
    }
}
