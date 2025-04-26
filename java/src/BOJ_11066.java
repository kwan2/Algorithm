import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11066 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {

            int K = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int[] file = new int[K + 1];
            int[] fileSum = new int[K + 1];
            int[][] dp = new int[K + 1][K + 1];

            for(int i = 1; i <= K; i++) {
                file[i] = Integer.parseInt(st.nextToken());
                fileSum[i] = file[i] + fileSum[i - 1];
            }

            for(int i = 1; i <= K; i++) {
                for(int start = 1; start + i <= K; start++) {

                    int end = start + i;
                    dp[start][end] = Integer.MAX_VALUE;

                    for(int mid = start; mid < end; mid++) {
                        dp[start][end] = Math.min(
                                dp[start][end],
                                dp[start][mid] + dp[mid + 1][end] + fileSum[end] - fileSum[start - 1]
                        );
                    }
                }
            }


            System.out.println(dp[1][K]);

        }

    }
}
