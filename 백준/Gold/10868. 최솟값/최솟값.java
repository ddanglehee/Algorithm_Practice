import java.util.*;
import java.io.*;

public class Main {
	
	static int N, M;
	static int[] arr;
	static int[] bucket;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		int bs = (int) Math.sqrt(N);
		bucket = new int[N / bs + 1];
		Arrays.fill(bucket, Integer.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			int bucketIndex = i / bs;
			bucket[bucketIndex] = Math.min(bucket[bucketIndex], arr[i]);
		}
		
		int a,b, result;
		StringBuilder sb = new StringBuilder();
		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken()) - 1;
			b = Integer.parseInt(st.nextToken()) - 1;
			result = Integer.MAX_VALUE;
			for (int i = a; i <= b; ) {
				if (i % bs == 0 && i + bs - 1 <= b) {
					result = Math.min(result, bucket[i / bs]);
					i += bs;
				} else {
					result = Math.min(result, arr[i++]);
				}
			} 
			
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}
