import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_9184
 * @date : 25. 8. 4.
 */
public class BOJ_9184 {

    private static int[][][] dp = new int[21][21][21];

    private static boolean inRange(int a, int b, int c) {
        return a < 21 && b < 21 && c < 21 && a >= 0 && b >= 0 && c >= 0;
    }
    private static int w(int a, int b, int c) {
        if(inRange(a, b, c) && dp[a][b][c] != 0) {
            return dp[a][b][c];
        }

        if(a <= 0 || b <= 0 || c <= 0) {
            return 1;
        }

        if(a > 20 || b > 20 || c > 20) {
            return dp[20][20][20] = w(20, 20, 20);
        }

        if(a < b && b < c) {
            return dp[a][b][c] = w(a, b, c - 1) + w(a, b- 1, c- 1) - w(a, b- 1, c);
        }

        return dp[a][b][c] = w(a - 1 ,b, c) + w(a - 1 , b - 1, c) + w(a - 1, b, c - 1)- w(a - 1 , b - 1, c - 1);
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder stringBuilder = new StringBuilder();

        while(true) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(stringTokenizer.nextToken());
            int b = Integer.parseInt(stringTokenizer.nextToken());
            int c = Integer.parseInt(stringTokenizer.nextToken());

            if(a == -1 && b == -1 && c == -1) {
                break;
            }

            stringBuilder.append("w(")
                    .append(a).append(", ")
                    .append(b).append(", ")
                    .append(c).append(") = ")
                    .append(w(a, b, c)).append("\n");
        }

        System.out.println(stringBuilder.toString());
    }
}
