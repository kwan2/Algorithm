import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10830 {


    public static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[A.length][A[0].length];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < A[i].length; j++) {
                for(int k = 0; k < A[0].length; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                    result[i][j] %= 1000;
                }
            }
        }

        return result;
    }
    public static int[][] divideConquer(int[][] matrix, long b) {
        if(b == 1) {
            return matrix;
        }

        int[][] result = divideConquer(matrix, b / 2);

        result = multiply(result, result);

        if(b % 2 == 1) {
            result = multiply(result, matrix);
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
            }
        }
        int[][] answer = divideConquer(matrix, B);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }
}
