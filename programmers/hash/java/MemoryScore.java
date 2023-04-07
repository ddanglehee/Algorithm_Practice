import java.util.*;

class Solution {

    Map<String, Integer> yearningMap = new HashMap<>();

    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        int[] answer = new int[photo.length];

        for (int i = 0; i < name.length; i++) {
            yearningMap.put(name[i], yearning[i]);
        }

        for (int i = 0; i < photo.length; i++) {
            String[] persons = photo[i];
            int score = 0;

            for (String person: persons) {
                if (yearningMap.get(person) == null) continue;
                score += yearningMap.get(person);
            }

            answer[i] = score;
        }

        return answer;
    }
}