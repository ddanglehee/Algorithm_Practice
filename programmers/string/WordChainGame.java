import java.util.*;

class WordChainGame {
    public int[] solution(int n, String[] words) {
        int[] answer = {0, 0};
        Set<String> usedWords = new HashSet<String>();
        usedWords.add(words[0]);

        char preAlphabet = words[0].charAt(words[0].length() - 1);
        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.length() == 1 || currentWord.charAt(0) != preAlphabet || usedWords.contains(currentWord)) {
                answer[0] = i % n + 1;
                answer[1] = i / n + 1;
                break;
            }
            usedWords.add(currentWord);
            preAlphabet = currentWord.charAt(currentWord.length() - 1);
        }

        return answer;
    }
}
