import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942 {

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] text = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for(int i = 1; i <= N; i++) {
            text[i] = Integer.parseInt(st.nextToken());
        }


        int[][] dp = new int[N + 1][N + 1];

        // 1. 팰린드롬 갱신
        // 1-1. 자기자신은 true
        for(int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 1-3. 크기가 2인데 같으면 true
        for(int i = 1; i < N; i++) {
            if (text[i] == text[i + 1]) {
               dp[i][i + 1] = 1;
            }
        }

        // 1-4. 크기가 3이상인 경우
        for(int i = 3; i <= N; i++) {
            for(int j = 1; j <= N - i + 1; j++) {
                int k = j + i - 1;
                // 맨앞과 맨뒤 동일하고, 가운데 2,3,... 칸들이 서로 동일한지 => 크기 별로 검증을 진행하기 때문에 가능.
                if(text[j] == text[k] && dp[j + 1][k - 1] == 1) {
                    dp[j][k] = 1;
                }
            }
        }


        int M = Integer.parseInt(br.readLine());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            answer.append(dp[a][b]).append("\n");
        }

        System.out.println(answer);
    }
}
