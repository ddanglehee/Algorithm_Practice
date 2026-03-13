import java.util.*;
import java.io.*;

class Main {
    
    static int N, M, K;
    static long tree[];
    static long lazy[];
    static long arr[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        tree = new long[4 * N];
        lazy = new long[4 * N];
        arr = new long[N + 1];
        
        for (int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        
        init(1, 1, N);
        
        int Q = M + K;
        int a, b, c;
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            
            if (a == 1) {
                long d = Long.parseLong(st.nextToken());
                update(1, 1, N, b, c, d);
            }  else {
                sb.append(query(1, 1, N, b, c)).append("\n");
            }
        }
        
        System.out.print(sb);
    }
    
    static void propagate(int node, int start, int end) {
        if (lazy[node] != 0) {
            tree[node] += lazy[node] * (end - start + 1);
            
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            
            lazy[node] = 0;
        }
    }
    
    static void update(int node, int start, int end, int left, int right, long diff) {
        propagate(node, start, end);
        
        if (end < left || right < start) return;
        
        if (left <= start && end <= right) {
            lazy[node] += diff;
            propagate(node, start, end);
            return;
        }
        
        int mid = (start + end) / 2;
        update(node * 2, start, mid, left, right, diff);
        update(node * 2 + 1, mid + 1, end, left, right, diff);
        
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }
    
    static long query(int node, int start, int end, int left, int right) {
        propagate(node, start, end);
        if (end < left || right < start) return 0;
        
        if (left <= start && end <= right) return tree[node];
        
        int mid = (start + end) / 2;
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right);
    }
    
    static long init(int node, int start, int end) {
        if (start == end) return tree[node] = arr[start];
        
        int mid = (start + end) / 2;
        return tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
    }
}