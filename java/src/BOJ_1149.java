import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] rgbCost = new int[n + 1][3];

        // 해당 단계에서 색깔별 모든 경우의 수를 구한다.
        int[][] dp = new int[1001][3];

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            rgbCost[i][0] = Integer.parseInt(st.nextToken());
            rgbCost[i][1] = Integer.parseInt(st.nextToken());
            rgbCost[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++) {
            dp[1][i] = rgbCost[1][i];
        }


        for(int i = 2; i <= n; i++) {
            dp[i][0] += rgbCost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] += rgbCost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] += rgbCost[i][2] + Math.min(dp[i-1][1], dp[i-1][0]);
        }


        System.out.println(Math.min(dp[n][0] , Math.min(dp[n][1], dp[n][2])));




    }
}
