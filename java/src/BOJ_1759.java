import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description : 암호 만들기
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_1759
 * @date : 25. 4. 29.
 */
public class BOJ_1759 {

    private static int L, C;
    private static String[] text;
    private static List<String> answer;

    private static void dfs(int depth, int index, String ans) {
        if(depth == L) {

            if(valid(ans) && ans.length() == L && !answer.contains(ans)) {
                answer.add(ans);
            }

            return;
        }

        for(int i = index; i < C; i++) {
            ans += text[i];
            dfs(depth + 1, i + 1, ans);
            ans = ans.substring(0, ans.length() - 1);
        }

    }

    private static boolean valid(String ans) {

        int num1 = 0, num2 = 0;

        for(int i = 0; i < ans.length(); i++) {
            char c = ans.charAt(i);

            if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                num1++;
            } else{
                num2++;
            }
        }

        return num1 >= 1 && num2 >= 2;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        text = new String[C];
        answer = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");


        for(int i = 0; i < C; i++) {
            String t = st.nextToken();
            text[i] = t;
        }

        Arrays.sort(text);

        dfs(0, 0, "");

        for(String ans : answer){
            System.out.println(ans);
        }

    }
}
