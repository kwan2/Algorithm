import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_1522
 * @date : 25. 4. 22.
 */
public class BOJ_1522 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();

        int n = text.length();

        int aCount = 0, bCount = 0, start = 0, end = n - 1;

        for(int i = 0; i < n; i++) {
            if(text.charAt(i) == 'a') {
                aCount++;
            }
        }

        for(int i = 0; i < aCount; i++) {
            if(text.charAt(i) == 'b') {
                bCount++;
            }
        }

        int answer = bCount;

        while(start < n){
            if(text.charAt(++end % n) == 'b') {
                bCount++;
            }

            if(text.charAt(start++) == 'a') {
                bCount--;
            }
            answer =  Math.min(answer, bCount);
        }
        System.out.println(answer);

    }
}
