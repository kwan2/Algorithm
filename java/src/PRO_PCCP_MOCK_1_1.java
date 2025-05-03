import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : jung-kwanhee
 * @description : PCCP 모의고사 1회 1번
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_PCCP_MOCK_1_1
 * @date : 25. 5. 3.
 */
public class PRO_PCCP_MOCK_1_1 {
    public static String solution(String input_string) {

        List<Character> answer = new ArrayList<>();
        int[] alpabet = new int[27];

        int idx = 0;

        while(idx < input_string.length()){
            char cur = input_string.charAt(idx);
            int nextIdx = idx + 1;

            for(; nextIdx < input_string.length(); nextIdx++){
                char next = input_string.charAt(nextIdx);

                if(next != cur) {
                    break;
                }
            }

            idx = nextIdx;
            int a = (cur - 'a') % 26 + 1;
            alpabet[a]++;
        }

        for(int i = 0; i < 27; i++) {
            if(alpabet[i] >= 2) {
                char c = (char)((i - 1) + 'a');
                answer.add(c);
            }
        }

        Collections.sort(answer);
        String answerStr = "";
        for(int i = 0; i < answer.size(); i++){
            answerStr += answer.get(i);
        }

        return answerStr == "" ? "N" : answerStr;

    }

    public static void main(String[] args) {

        String inputString = "eeddee";
        System.out.println(solution(inputString));
    }
}
