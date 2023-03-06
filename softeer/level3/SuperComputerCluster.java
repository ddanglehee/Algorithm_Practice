import java.util.*;
import java.io.*;


public class SuperComputerCluster
{
    private static int answer;
    private static int computerCount;
    private static long budget;
    private static final Set<Integer> performanceSet = new HashSet<>();
    private static final Map<Integer, Integer> performanceMap = new HashMap<>();
    private static List<Integer> performanceList;

    public static void main(String args[]) throws IOException
    {
        init();

        System.out.print(findPossiblePerformance(performanceList.get(0), 1010000000));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        computerCount = Integer.parseInt(st.nextToken());
        budget = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < computerCount; i++) {
            int performance = Integer.parseInt(st.nextToken());
            performanceSet.add(performance);
            performanceMap.put(performance, performanceMap.getOrDefault(performance, 0) + 1);
        }

        performanceList = new ArrayList<>(performanceSet);
        Collections.sort(performanceList);
        answer = performanceList.get(0);
    }

    private static int findPossiblePerformance(int start, int end) {
        int result = start;

        while (start <= end) {
            int mid = (start + end) / 2;

            if (canUpdate(mid)) {
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return result;
    }

    private static boolean canUpdate(int to) {
        long tmp = 0;

        for (int performance: performanceList) {
            if (performance < to) {
                tmp += (long) performanceMap.get(performance) * Math.pow(to - performance, 2);
            } else {
                break;
            }
        }

        return 0 <= (budget - tmp);
    }
}