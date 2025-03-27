import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 소가 길을 건너간 이유 3
public class BOJ_14469 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int arrival = 0;
        int N = Integer.parseInt(br.readLine());

        int[][] times = new int[N][2];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i][0] = Integer.parseInt(st.nextToken());
            times[i][1] = Integer.parseInt(st.nextToken());
        }

        // 소가 도착한 순서대로 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        for(int[] time : times) {
            if(arrival < time[0]) {
                arrival = time[0] + time[1];
            } else{
                arrival += time[1];
            }
        }

        System.out.println(arrival);

    }
}
