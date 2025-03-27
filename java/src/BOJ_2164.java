import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2164 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()), count = 0;

        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i < N + 1; i++) {
            queue.offer(i);
        }

        while(queue.size() > 1) {
            int num = queue.poll();
            count++;
            if(count % 2 == 0) {
                queue.offer(num);
            }
        }

        System.out.println(queue.poll());


    }
}
