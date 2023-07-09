import java.util.ArrayDeque;
import java.util.Deque;

public class Pro두큐합같게만들기 {
    int[] initialSum;
    Deque<Integer> q1;
    Deque<Integer> q2;

    private boolean init(int[] queue1, int[] queue2){
        for(int i = 0; i < queue1.length; i++){
            q1.add(queue1[i]);
            q2.add(queue2[i]);
            initialSum[0] += queue1[i];
            initialSum[1] += queue2[i];
        }
        initialSum[2] = initialSum[0] + initialSum[1];
        if(initialSum[2] % 2 != 0) return false;
        else{
            initialSum[2] /= 2;
            return true;
        }
    }

    public int solution(int[] queue1, int[] queue2) {

        int answer = 0;

        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        initialSum = new int[3];
        if(!init(queue1, queue2)) return -1;

        while(answer != 4 * queue1.length){
            if(initialSum[0] == initialSum[1])
                return answer;
            else if(initialSum[0] > initialSum[1]){
                Integer v = q1.removeFirst();
                initialSum[0] -= v;
                initialSum[1] += v;
                q2.addLast(v);
            }
            else if(initialSum[0] < initialSum[1]){
                int v = q2.removeFirst();
                initialSum[0] += v;
                initialSum[1] -= v;
                q1.addLast(v);
            }
            answer++;
        }
        return -1;
    }
    public static void main(String[] args) {
        Pro두큐합같게만들기 p = new Pro두큐합같게만들기();
        int []q1 = {3, 2, 7, 2};
        int []q2 = {4, 6, 5, 1};
        System.out.println(p.solution(q1,q2));
    }
}
