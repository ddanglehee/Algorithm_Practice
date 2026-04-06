import java.util.*;
import java.io.*;

public class Main {

    static int N, root;
    static ArrayList<Integer>[] childrenOf;
    static boolean[] isRemoved;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        childrenOf = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            childrenOf[i] = new ArrayList<>();
        }
        isRemoved = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                childrenOf[parent].add(i);
            }
        } 

        int removeTarget = Integer.parseInt(br.readLine());
        isRemoved[removeTarget] = true;

        if (!isRemoved[root]) {
            dfs(root);
        }

        System.out.print(answer);
    }

    static void dfs(int n) {
        int removedChildrenCount = 0;

        for (int child : childrenOf[n]) {
            if (isRemoved[child]) {
                removedChildrenCount++;
                continue;
            }
            dfs(child);
        }

        if (childrenOf[n].size() - removedChildrenCount == 0) answer++;
    }
}