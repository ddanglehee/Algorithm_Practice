import java.util.*;
import java.io.*;

public class Boj19598 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Meeting> meetingList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetingList.add(new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        meetingList.sort(Comparator.comparing(Meeting::getStart));

        PriorityQueue<Meeting> pq = new PriorityQueue<>(Comparator.comparing(Meeting::getEnd));
        for (Meeting meeting: meetingList) {
            if (pq.isEmpty()) {
                pq.add(meeting);
            } else {
                Meeting meetingInProcess = pq.peek();

                if (meetingInProcess.end <= meeting.start) {
                    pq.poll();
                }
                pq.add(meeting);
            }
        }

        System.out.print(pq.size());
    }

    static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}
