import java.io.*;
import java.util.*;

public class Boj8979 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<Country> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());

            pq.offer(new Country(id, gold, silver, bronze));
        }

        int rank = 1;
        int count = 0;
        int preGold = 0;
        int preSilver = 0;
        int preBronze = 0;
        while (!pq.isEmpty()) {
            Country cur = pq.poll();
            count++;

            if (preGold != cur.gold || preSilver != cur.silver || preBronze != cur.bronze) {
                rank = count;
                preGold = cur.gold;
                preSilver = cur.silver;
                preBronze = cur.bronze;
            }

            if (k == cur.id) {
                System.out.println(rank);
                return;
            }
        }
    }

    public static class Country implements Comparable<Country> {
        int id;
        int gold;
        int silver;
        int bronze;

        public Country(int id, int gold, int silver, int bronze) {
            this.id = id;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }

        @Override
        public int compareTo(Country o) {
            if (this.gold != o.gold) {
                return o.gold - this.gold;
            } else if (this.silver != o.silver) {
                return o.silver - this.silver;
            } else {
                return o.bronze - this.bronze;
            }
        }
    }
}
