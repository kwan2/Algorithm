import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629 {

    public static long divideConquer(int A, int B, int C) {

        if(B == 1) {
            return A % C;
        }

        long temp = divideConquer(A, B / 2, C);

        // 홀수인 경우
        if(B % 2 == 1) {
            return temp % C * temp % C * A % C;
        }

        return temp % C * temp % C % C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());


        System.out.println(divideConquer(A, B, C));
    }
}
