import javax.sound.midi.Soundbank;
import java.util.PriorityQueue;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_야근지수
 * @date : 25. 5. 5.
 */
public class PRO_야근지수 {
    public static long solution(int n, int[] works) {
        long totalWork = 0;
        long answer = 0;

        // 제곱의 합이므로 가장 큰 작업의 양을 줄이는게 최선이다.
        PriorityQueue<Integer> pq = new PriorityQueue<>((w1, w2) -> -1 * Integer.compare(w1, w2));

        for(int i = 0 ; i < works.length; i++) {
            totalWork += works[i];
            pq.offer(works[i]);
        }

        if(totalWork > n) {

            // 앞에서 부터 줄인다.
            for(int i = 0; i < n; i++) {
                pq.offer(pq.poll() - 1);
            }
            // 합을 구한다.
            while(!pq.isEmpty()) {
                int job = pq.poll();
                answer += Math.pow(job, 2);
            }
        }

        return answer;
    }

    public static void main(String[] args) {

        int[] works = new int[] { 4, 3, 3 };
        int n = 4;

        System.out.println(solution(n, works));
    }
}
