import java.util.Arrays;

/**
 * @author : jung-kwanhee
 * @description : PCCP 모의고사 1회 3번 문제 : 유전법치
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_PCCP_MOCK_1_3
 * @date : 25. 5. 3.
 */
public class PRO_PCCP_MOCK_1_3 {

    private static String find(int n, long p) {
        if (n == 1) {
            return "Rr";
        }

        String parentType = find(n - 1, (p - 1) / 4 + 1);

        if (parentType.equals("RR") || parentType.equals("rr")) {
            return parentType;
        }

        long group = (p - 1) % 4 + 1;

        if (group == 1) {
            return "RR";
        } else if (group == 2 || group == 3) {
            return "Rr";
        } else {
            return "rr";
        }
    }
    public static String[] solution(int[][] queries) {
        return Arrays.stream(queries)
                .map(query -> find(query[0], query[1]))
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        int[][] queries = new int[][] { { 5, 60} };

        String[] answer = solution(queries);

        for(int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}
