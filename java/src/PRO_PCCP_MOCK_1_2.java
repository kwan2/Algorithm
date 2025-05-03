/**
 * @author : jung-kwanhee
 * @description : PCCP 모의고사 1회 2번 문제
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_PCCP_MOCK_1_2
 * @date : 25. 5. 3.
 */
public class PRO_PCCP_MOCK_1_2 {
    private static int row, col;
    private static int answer;
    private static boolean[] visit;
    private static void dfs(int depth, int count, int[][] ability) {

        // 전체 종목이 다 포함되면 계산
        if(depth == col) {
            answer = Math.max(count, answer);
            return;
        }

        for(int i = 0; i < row; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(depth + 1, count + ability[i][depth], ability);
                visit[i] = false;
            }
        }
    }
    public static int solution(int[][] ability) {
        // 학생 수
        row = ability.length;

        // 종목 수
        col = ability[0].length;

        visit = new boolean[row];

        answer = Integer.MIN_VALUE;

        dfs(0, 0, ability);

        return answer;
    }

    public static void main(String[] args) {
        int[][] ability = {{20, 30}, {30, 20}, {20, 30}};

        System.out.println(solution(ability));
    }
}
