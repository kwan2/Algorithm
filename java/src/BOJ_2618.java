import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2618 {

    static class Point {
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int totalEvent;
    private static int[][] dp;
    private static Point[] eventPoints;


    private static int getDistance(int policeNum, int startPoint, int destPoint) {

        Point policePoint;
        Point eventPoint = eventPoints[destPoint];
        if(startPoint == 0) {
            policePoint = policeNum == 1
                    ? new Point(1, 1)
                    : new Point(totalEvent, totalEvent);

        } else {
            policePoint = eventPoints[startPoint];
        }
        return Math.abs(eventPoint.x - policePoint.x) + Math.abs(eventPoint.y - policePoint.y);

    }
    /**
     *
     * @param eventNum : 사건 번호
     * @param currentOne : 현 시점에서 가장 최근 경찰차1이 출동한 사건 번호
     * @param currentTwo : 현 시점에서 가장 최근 경찰차2이 출동한 사건 번호
     * @return 해당 step 에서의 최소 거리
     */
    private static int dfs(int eventNum, int currentOne, int currentTwo) {

        // 탐색중인 이벤트 넘버가 총 이벤트 개수 보다 큰 경우
        if(eventNum > totalEvent) {
            return 0;
        }

        // 이미 갔던 넘버이면
        if(dp[currentOne][currentTwo] != 0) {
            return dp[currentOne][currentTwo];
        }

        // 경찰차들의 현재 위치로 부터 사건에 대한 거리
        int oneMoveLength = dfs(eventNum + 1, eventNum, currentTwo) + getDistance(1, currentOne, eventNum);
        int twoMoveLength = dfs(eventNum + 1, currentOne, eventNum) + getDistance(2, currentTwo, eventNum);

        dp[currentOne][currentTwo] = Math.min(oneMoveLength, twoMoveLength);

        return dp[currentOne][currentTwo];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        totalEvent = Integer.parseInt(br.readLine());

        int[] answer = new int[totalEvent + 1];
        eventPoints = new Point[totalEvent + 1];

        for(int i = 1; i <= totalEvent; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            eventPoints[i] = new Point(x, y);
        }

        dp = new int[totalEvent + 1][totalEvent + 1];

        int minAnswer = dfs(1, 0, 0);
        int one = 0, two = 0;
        for (int i = 1; i <= totalEvent; i++) {
            int minDistance = dfs(i, one, i);

            if(dp[one][two] - minDistance == dp[i][two]) {
                one = i;
                answer[i] = 1;
            } else {
                two = i;
                answer[i] = 2;
            }
        }


        System.out.println(minAnswer);

        for(int i = 1; i <= totalEvent; i++) {
            System.out.println(answer[i]);
        }


    }
}
