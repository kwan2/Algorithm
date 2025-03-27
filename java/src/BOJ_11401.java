import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11401 {

    public static void main(String[] args) throws IOException {
        final long VALUE = 1000000007;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        if(K == 0 || K == N) {
            System.out.println(1);
        } else {
            // nCk 조합.
            int[][] dp = new int[N + 1][K + 1];
            
        }




    }
}
