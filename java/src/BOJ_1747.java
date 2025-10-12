import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : jung-kwanhee
 * @description : 소수 & 팰린드롬
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_1747
 * @date : 25. 10. 12.
 */
public class BOJ_1747 {

    private static boolean isPalindrome(int target) {
        String s = String.valueOf(target);
        int len = s.length();

        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPrime (int target) {

        if(target == 1) return false;

        for(int i = 2; i * i <= target; i++) {
            if(target % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 소수 이면서 팰린드롬
        for(int i = N; i <= Integer.MAX_VALUE; i++) {

            if(!isPalindrome(i)) {
                continue;
            }

            if(isPrime(i)) {
                System.out.println(i);
                break;
            }

        }


    }
}
