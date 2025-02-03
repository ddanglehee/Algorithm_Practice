import java.util.*;
import java.io.*;

public class Boj4659 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        boolean containVowel;
        boolean isAcceptable;
        char pre;
        int vowelCount; int consonantCount;
        HashSet<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');

        while(true) {
            String password = br.readLine();

            if (password.equals("end")) break;

            containVowel = false;
            isAcceptable = true;
            pre = ' ';
            vowelCount = 0;
            consonantCount = 0;
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (vowelSet.contains(c)) {
                    if (vowelCount == 0) consonantCount = 0;
                    vowelCount++;
                } else {
                    if (consonantCount == 0) vowelCount = 0;
                    consonantCount++;
                }

                if (vowelCount == 3 || consonantCount == 3 || (c == pre && (c != 'e' && c != 'o'))) {
                    isAcceptable = false;
                    break;
                }

                if (!containVowel && vowelSet.contains(c)) {
                    containVowel = true;
                }

                pre = c;
            }

            if (isAcceptable && containVowel) {
                sb.append('<').append(password).append('>').append(" is acceptable.\n");
            } else {
                sb.append('<').append(password).append('>').append(" is not acceptable.\n");
            }
        }

        System.out.println(sb);
    }
}
