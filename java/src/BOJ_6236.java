import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_6236
 * @date : 25. 8. 4.
 */
public class BOJ_6236 {

    private static int N, M;
    private static int[] expense;

    private static boolean isNotOverM (int cost) {
        int count = 1;
        int balance = cost;

        for(int i = 0; i < N; i++) {
            int exp = expense[i];

            if(balance < exp) {
                count += 1;
                balance = cost;
            }
            balance -= exp;
        }
        return count <= M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        expense = new int[N];

        int maxExpense = 0;
        int sumExpense = 0;

        for(int i = 0; i < N; i++) {
            expense[i] = Integer.parseInt(br.readLine());
            maxExpense = Math.max(maxExpense, expense[i]);
            sumExpense += expense[i];
        }

        int answer = maxExpense;

        while(maxExpense <= sumExpense) {
            int mid = (maxExpense + sumExpense) / 2;

            if(isNotOverM(mid)) {
                answer = mid;
                sumExpense = mid - 1;
            } else {
                maxExpense = mid + 1;
            }
        }

        System.out.println(answer);

    }
}
