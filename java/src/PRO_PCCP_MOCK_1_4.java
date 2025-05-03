import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author : jung-kwanhee
 * @description : PCCP 모의고사 1회 4번 문제
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_PCCP_MOCK_1_4
 * @date : 25. 5. 3.
 */
public class PRO_PCCP_MOCK_1_4 {
    public static class Process implements Comparable<Process> {
        int point;
        int processTime;
        int callTime;

        Process(int point, int processTime, int callTime) {
            this.point = point;
            this.processTime = processTime;
            this.callTime = callTime;
        }

        @Override
        public int compareTo(Process other) {
            // 우선순위 순으로 정렬
            int comparePoint = Integer.compare(this.point, other.point);
            if(comparePoint == 0) {
                return Integer.compare(this.callTime, other.callTime);
            }
            return comparePoint;
        }
    }


    public static long[] solution(int[][] program) {
        long[] answer = new long[11];

        PriorityQueue<Process> pq = new PriorityQueue<>();

        Arrays.sort(program, (p1, p2) -> {
            int compareCallTime = Integer.compare(p1[1], p2[1]);
            if(compareCallTime == 0) {
                return Integer.compare(p1[0], p2[0]);
            }
            return compareCallTime;
        });

        int currentTime = 0, checkCount = 0;

        while(checkCount < program.length || !pq.isEmpty() ) {
            for(int i = checkCount; i < program.length; i++) {
                if (currentTime < program[i][1]) {
                    break;
                } else {
                    pq.offer(new Process(program[i][0], program[i][2], program[i][1]));
                    checkCount++;
                }
            }

            if(!pq.isEmpty()) {
                Process curProcess = pq.poll();
                // 현재 시각에서 프로세스의 호출 시에서 기다린 시간이 웨이팅 시간
                long waitingTime = currentTime - curProcess.callTime;
                answer[curProcess.point] += waitingTime;
                currentTime += curProcess.processTime;
            } else {
                currentTime = program[checkCount][1];
            }
        }

        answer[0] = currentTime;

        return answer;

    }

    public static void main(String[] args) {
        int[][] program = {{2, 0, 10}, {1, 5, 5}, {3, 5, 3}, {3, 12, 2}};

        long[] answer = solution(program);
    }
}
