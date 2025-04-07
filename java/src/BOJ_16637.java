import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : jung-kwanhee
 * @description : 괄호 추가하기
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_16637
 * @date : 25. 4. 7.
 */

public class BOJ_16637 {

    private static int answer;
    private static List<Integer> nums;
    private static List<String> operations;


    private static int calc (int num1, int num2, String operate) {
        int result = 0;
        switch(operate) {
            case "+":
                result = num1 + num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "-":
                result = num1 - num2;
                break;
        }

        return result;
    }

    private static void dfs (int index, int currentNum) {
        if(index == nums.size() - 1) {
            answer = Math.max(answer, currentNum);
            return;
        }

        dfs(index + 1, calc(currentNum, nums.get(index + 1), operations.get(index)));

        if(index + 2 <= nums.size() - 1) {
            int newNum = calc(nums.get(index + 1), nums.get(index + 2), operations.get(index + 1));
            dfs(index + 2, calc(currentNum, newNum, operations.get(index)));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();
        operations = new ArrayList<>();

        String text = br.readLine();
        for(int i = 0; i < N; i++) {
            if(i % 2 == 0) {
                nums.add(text.charAt(i) - '0');
            } else{
                operations.add(String.valueOf(text.charAt(i)));
            }
        }

        answer = Integer.MIN_VALUE;

        dfs(0, nums.get(0));

        System.out.println(answer);

    }
}
