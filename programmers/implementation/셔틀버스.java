import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Time answer = new Time("09:00");

        // 1. 운행하는 버스 시간 배열 만들기
        Time[] busTime = new Time[n];
        int busH = 9;
        int busM = 0;
        busTime[0] = new Time(busH, busM);
        for (int i = 1; i < n; i++) {
            busM += t;
            if (60 <= busM) {
                busH++;
                busM -= 60;
            }
            busTime[i] = new Time(busH, busM);
        }

        // 2. timetable 배열을 커스텀 클래스인 Time의 배열로 치환
        Time[] timeArray = new Time[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            timeArray[i] = new Time(timetable[i]);
        }
        Arrays.sort(timeArray);

        // 3. 답 구하기
        int crewIdx = 0;
        int curCrewCount;
        // 3-1. 모든 버스 시간표에 대해 (busTime)
        for (int i = 0; i < n; i++) {
            curCrewCount = 0;

            // 3-2. 지금 버스에 태울 수 있는 모든 크루를 태운다.
            while (crewIdx < timeArray.length) {
                if (timeArray[crewIdx].compareTo(busTime[i]) == -1) {
                    curCrewCount++;
                } else {
                    break;
                }
                crewIdx++;

                // 3-2-1. 지금 버스에 정원이 다 찼으면, 이 버스에 가장 마지막으로 탄 크루보다 1분 빨리 오면 이 버스를 탈 수 있다.
                if (m == curCrewCount) {
                    Time crew = timeArray[crewIdx - 1];
                    if (crew.m == 0) {
                        answer = new Time(crew.h - 1, 59);
                    } else {
                        answer = new Time(crew.h, crew.m - 1);
                    }
                    break;
                }
            }

            // 3-2-2. 지금 버스에 정원이 다 안 찼으면, 이 버스 도착 시간에 도착하면 이 버스를 탈 수 있다.
            if (curCrewCount < m) {
                answer = busTime[i];
            }
        }

        return answer.toString();
    }

    class Time implements Comparable<Time> {
        private int h;
        private int m;

        public Time(int h, int m) {
            this.h = h;
            this.m = m;
        }

        public Time(String time) {
            StringTokenizer st = new StringTokenizer(time, ":");
            this.h = Integer.parseInt(st.nextToken());
            this.m = Integer.parseInt(st.nextToken());
        }

        @Override
        public int compareTo(Time o) { // -1이면 이 객체가 더 빠른 시각
            if (this.h < o.h) {
                return -1;
            }  else if (this.h == o.h && this.m <= o.m) {
                return -1;
            }
            return 1;
        }

        public int getH() {
            return h;
        }

        public int getM() {
            return m;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (h < 10) {
                sb.append(0);
            }
            sb.append(h).append(":");
            if (m < 10) {
                sb.append(0);
            }
            sb.append(m);
            return sb.toString();
        }
    }
}