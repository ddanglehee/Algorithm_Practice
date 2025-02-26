import java.io.*;
import java.util.*;

public class Boj1497 {

    static int answer = 11;
    static int answerMusicCount = 0;
    static int n, m;
    static Guitar[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new Guitar[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String music = st.nextToken();
            music = music.replace('N', '0');
            music = music.replace('Y', '1');
            arr[i] = new Guitar(name, music);
        }


        for (int i = 1; i <= n; i++) {
            solve(1, i, 0, 0);
        }

        if (answer == 11) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    private static void solve(int curCount, int targetCount, int start, long preMusic) {
        for (int i = start; i < n; i++) {
            long curMusic = preMusic | Long.parseLong(arr[i].music, 2);

            if (curMusic == 0) continue;

            if (curCount == targetCount) {

                int musicCount = Long.bitCount(curMusic);

                if (answerMusicCount < musicCount) {
                    answer = targetCount;
                    answerMusicCount = musicCount;
                } else if (answerMusicCount == musicCount && targetCount < answer) {
                    answer = targetCount;
                }
            } else {
                solve(curCount + 1, targetCount, i + 1, curMusic);
            }
        }
    }

    static class Guitar {
        String name;
        String music;

        public Guitar(String name, String music) {
            this.name = name;
            this.music = music;
        }
    }
}
