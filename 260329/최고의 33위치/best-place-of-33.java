import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        
        int answer = 0;
        for (int i = 0; i <= n - 3; i++) {
    
            for (int j = 0; j <= n - 3; j++) {
                int sum = 0;
                for (int r = 0;  r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        sum += grid[i + r][j + c];
                    }
                }
                answer = Math.max(answer, sum);
            }
        }
        System.out.print(answer);
    }
}