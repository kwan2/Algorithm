import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : jung-kwanhee
 * @description : 1, 2, 3 더하기 4
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_15989
 * @date : 25. 4. 21.
 */
public class BOJ_15989 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());

            // i를 j개의 숫자를 사용한 경우의 수
            // 문제 사항 상, 순서 상관 없으므로 중복조합
            int[][] dp = new int[10001][4];

            dp[1][1] = 1; // 1
            dp[2][1] = 1; // 1 + 1
            dp[2][2] = 1; // 2
            dp[3][1] = 1; // 1 + 1 + 1
            dp[3][2] = 1; // 2 + 1
            dp[3][3] = 1; // 3

            for(int i = 4; i <= 10000; i++) {
                dp[i][1] = 1; // 무조건 1 + 1 + 1...
                dp[i][2] = dp[i - 2][1] + dp[i - 2][2]; // 1을 만드는 경우 + 2를 만드는 경우
                dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]; // 1을 만드는 경우 + 2를 만드는 경우 + 3을 만드는 경우
                // 합의 법칙이므로 각각 + i 만큰 더해지는 것이다.
            }

            System.out.println(dp[n][3] + dp[n][2] + dp[n][1]);


        }
    }
}
