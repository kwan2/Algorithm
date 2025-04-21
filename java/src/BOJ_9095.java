import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_9095
 * @date : 25. 4. 21.
 */
public class BOJ_9095 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            int[] dp = new int[11];

            dp[1] = 1; // 1
            dp[2] = 2; // 1 + 1, 2
            dp[3] = 4; // 1 + 1 + 1, 2 + 1, 1 + 2

            for (int j = 4; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
            }

            System.out.println(dp[n]);
        }
    }
}
