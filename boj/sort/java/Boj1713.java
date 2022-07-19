import java.util.*;
import java.io.*;

public class Boj1713 {

    static int N, C;
    static ArrayList<Photo> frame = new ArrayList<>();
    static Photo[] photos = new Photo[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        C = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            int number = Integer.parseInt(st.nextToken());

            if (photos[number] == null) {
                photos[number] = new Photo(number, 0, 0, false);
            }

            if (photos[number].isIn) {
                photos[number].count++;
            } else { // 사진틀에 없는 경우
                if (frame.size() == N) {
                    Collections.sort(frame);
                    Photo photo = frame.remove(frame.size() - 1);
                    photo.isIn = false;
                }
                photos[number].count = 1;
                photos[number].isIn = true;
                photos[number].time = i;
                frame.add(photos[number]);
            }
        }

        Collections.sort(frame, Comparator.comparingInt(Photo::getStudentNumber));
        for (Photo photo : frame) {
            System.out.print(photo.studentNumber + " ");
        }
    }

    static class Photo implements Comparable<Photo> {

        int studentNumber;
        int count;
        int time;
        boolean isIn;

        public Photo(int studentNumber, int count, int time, boolean isIn) {
            this.studentNumber = studentNumber;
            this.count = count;
            this.time = time;
            this.isIn = isIn;
        }

        @Override
        public int compareTo(Photo o) {
            if (count > o.count) {
                return -1;
            } else if (count == o.count) {
                return o.time - time;
            } else {
                return 1;
            }
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        @Override
        public String toString() {
            return "Photo{" +
                    "studentNumber=" + studentNumber +
                    ", count=" + count +
                    ", time=" + time +
                    '}';
        }
    }
}
