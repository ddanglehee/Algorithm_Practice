import java.util.*;
import java.io.*;


public class GradeEvaluation
{
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static PriorityQueue<ScoreInfo> pq = new PriorityQueue<>(Comparator.comparing(ScoreInfo::getScore).reversed());
    static int[] scores;
    static int[] totalScores;
    static int[] ranks;

    public static void main(String args[]) throws IOException
    {
        N = Integer.parseInt(br.readLine());
        scores = new int[N + 1];
        totalScores = new int[N + 1];
        ranks = new int[N + 1];

        for (int contest = 1; contest <= 3; contest++) {
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int score = Integer.parseInt(st.nextToken());
                scores[i] = score;
                totalScores[i] += score;
            }
            calculateRank(scores);
        }
        calculateRank(totalScores);
        System.out.println(sb);
    }

    private static void calculateRank(int[] array) {
        for (int i = 1; i <= N; i++) {
            pq.offer(new ScoreInfo(i, array[i]));
        }

        int rank = 1;
        int beforeScore = -1; int beforeRank = 1;
        while (!pq.isEmpty()) {
            ScoreInfo scoreInfo = pq.poll();

            if (beforeScore == scoreInfo.score) {
                ranks[scoreInfo.id] = beforeRank;
            } else {
                ranks[scoreInfo.id] = rank;
                beforeScore = scoreInfo.score;
                beforeRank = rank;
            }
            rank++;
        }

        for (int i = 1; i <= N; i++) {
            sb.append(ranks[i]).append(" ");
        }
        sb.append("\n");
    }
}

class ScoreInfo {
    int id;
    int score;

    ScoreInfo(int id, int score) {
        this.id = id;
        this.score = score;
    }

    public int getScore() {
        return score;
    }
}
