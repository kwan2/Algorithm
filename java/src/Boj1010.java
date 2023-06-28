import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1010 {
    private static String dp(int N, int M) {
        // 0 < N <= M < 30
        // N == M 일 때, 1개의 경우의 수
        if (N == M) {
//            System.out.println(1);
            return "1";
        }

        // N < M 일 때, 교차 하면 안되기 때문에 mCn 조합 방법
        // 시간초과 해결을 위한 DP 방법론 적용
        int [][]dp = new int[31][31];

        for(int i =0; i<M; i++){
            dp[i][i] = 1;
            dp[i][0] = 1;
        }

        for(int i = 2; i <= M; i++){
            for(int j = 1; j <= N; j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }
        return Integer.toString(dp[M][N]);
//        System.out.println(dp[M][N]);

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i =0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            sb.append(dp(N,M)).append("\n");
        }
        System.out.print(sb);
    }
}
