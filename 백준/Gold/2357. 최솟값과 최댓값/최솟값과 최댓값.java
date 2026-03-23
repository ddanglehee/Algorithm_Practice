import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		int bucketSize = (int) Math.sqrt(N);
		int bucketCount = N / bucketSize + 1;
		int[] minBucket = new int[bucketCount];
		int[] maxBucket = new int[bucketCount];
		Arrays.fill(minBucket, Integer.MAX_VALUE);
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			int bucketIndex = i / bucketSize;
			minBucket[bucketIndex] = Math.min(minBucket[bucketIndex], n);
			maxBucket[bucketIndex] = Math.max(maxBucket[bucketIndex], n);
			arr[i] = n;
 		}

		StringBuilder sb = new StringBuilder();
		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			int min = Integer.MAX_VALUE;
			int max = 0;
			for (int i = a; i <= b;) {
				if (i % bucketSize == 0 && i + bucketSize - 1 <= b) {
					min = Math.min(min, minBucket[i / bucketSize]);
					max = Math.max(max, maxBucket[i / bucketSize]);
					
					i += bucketSize;
				} else {
					min = Math.min(min, arr[i]);
					max = Math.max(max, arr[i]);
					i++;
				}
			}
			
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
}
