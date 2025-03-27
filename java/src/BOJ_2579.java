import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] stairs = new int[N + 1] , dp = new int[N + 1];


        for(int i = 1; i <= N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = stairs[1];

        if(N >= 2) {
            dp[2] = stairs[1] + stairs[2];
        }


        for(int i = 3; i <= N; i++) {
            // 3개를 연달아서 밟을 수 없음
            // -> 3칸전의 메모지에이션 + 1칸전의 계단의 수는 무조건 규칙을 어긋나지 않음
            // -> 2칸전은 마찬가지로 현재 단계의 계단을 무조건 밟지 않음 + 이전 단계에 밟았어도 3칸전의 메모지에이션이기 때문에 한칸전과 지금은 연달아 밟을 수 있음.
            dp[i] = Math.max(dp[i-2], dp[i-3] + stairs[i - 1]) + stairs[i];
        }

        System.out.println(dp[N]);
    }
}
