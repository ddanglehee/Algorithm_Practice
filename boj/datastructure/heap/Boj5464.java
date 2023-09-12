import java.util.*;
import java.io.*;

public class Boj5464 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> emptyParkingSpace = new PriorityQueue<>();
        ArrayDeque<Integer> waiting = new ArrayDeque<>();
        int[] fee = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            fee[i] = Integer.parseInt(br.readLine());
            emptyParkingSpace.add(i);
        }

        Car[] car = new Car[m + 1];
        for (int i = 1; i <= m; i++) {
            car[i] = new Car(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        for (int i = 0; i < 2 * m; i++) {
            int number = Integer.parseInt(br.readLine());

            // 차 빼기
            if (number < 0) {
                number *= -1;
                emptyParkingSpace.add(car[number].parkingAt);
                if (!waiting.isEmpty()) {
                    int nxtNum = waiting.removeFirst();
                    car[nxtNum].parkingAt = emptyParkingSpace.poll();
                    answer += car[nxtNum].weight * fee[car[nxtNum].parkingAt];
                }
            }
            // 주차하기
            else {
                if (emptyParkingSpace.isEmpty()) {
                    waiting.add(number);
                } else {
                    car[number].parkingAt = emptyParkingSpace.poll();
                    answer += car[number].weight * fee[car[number].parkingAt];
                }
            }
        }

        System.out.print(answer);
    }

    static class Car {
        int weight;
        int parkingAt;

        public Car(int weight) {
            this.weight = weight;
        }

        public void setParkingAt(int parkingSpace) {
            this.parkingAt = parkingSpace;
        }
    }
}
