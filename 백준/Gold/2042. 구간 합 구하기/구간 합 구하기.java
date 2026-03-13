import java.util.*;
import java.io.*;

public class Main {
    
    static int N, M, K;
    static long[] arr;
    static long[] tree;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new long[N+1];
        tree = new long[4 * N];
        
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        initTree(1, 1, N);
        
        StringBuilder sb = new StringBuilder();
        int Q = M + K;
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            
            if (a == 1) {
                update(1, 1, N, b, c - arr[b]);
                arr[b] = c;
            } else {
                sb.append(query(1, 1, N, b, (int) c)).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    static void update(int node, int start, int end, int idx, long diff) {
        if (idx < start || end < idx) return;
        
        tree[node] += diff;
        
        if (start != end) {
            int mid = (start + end) / 2;
            update(node * 2, start, mid, idx, diff);
            update(node * 2 + 1, mid + 1, end, idx, diff);
        }
    }
    
    static long query(int node, int start, int end, int left, int right) {
        if (right < start || end < left) return 0;
        
        if (left <= start && end <= right) return tree[node];
        
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    }
    
    static long initTree(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        
        int mid = (start + end) / 2;
        return tree[node] = initTree(node * 2, start, mid) + initTree(node * 2 + 1, mid + 1, end);
    }
}
