import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Photo> map = new HashMap<>();
    static ArrayList<Integer> frame = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            int id = Integer.parseInt(st.nextToken());

            Photo photo = getPhoto(id);
            photo.count++;

            if (!frame.contains(id)) {
                photo.old = i;
                if (frame.size() == N) {
                    removePhoto();
                }
                frame.add(id);
            }
        }

        Collections.sort(frame);
        StringBuilder sb = new StringBuilder();

        for (int id : frame) {
            sb.append(id).append(" ");
        }
        System.out.print(sb);
    }

    static Photo getPhoto(int id) {
        Photo photo = map.get(id);

        if (photo == null) {
            photo = new Photo(id);
            map.put(id, photo);
        }

        return photo;
    }

    static void removePhoto() {
        Photo target = map.get(frame.get(0));

        for (int i = 1; i < frame.size(); i++) {
            int id = frame.get(i);
            Photo photo = getPhoto(id);
            if ((photo.count < target.count) ||
                    photo.count == target.count && photo.old < target.old) {
                target = photo;
            }
        }
        target.count = 0;
        frame.remove(Integer.valueOf(target.studentId));
    }
}

class Photo {
    int studentId;
    int old = 1000;
    int count = 0;

    public Photo(int studentId) {
        this.studentId = studentId;
    }
}