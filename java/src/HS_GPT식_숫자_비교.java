import java.io.*;
import java.util.*;

/**
 * @author : jung-kwanhee
 * @description : GPT식_숫자_비교
 * @packageName : PACKAGE_NAME
 * @fileName : HS_GPT식_숫자_비교
 * @date : 25. 4. 28.
 */


public class HS_GPT식_숫자_비교 {

    private static void sort(List<String> nums) {
        nums.sort((a, b) -> {
            String[] splitedA = a.split("\\.");
            String[] splitedB = b.split("\\.");
            int compareInteger = Integer.compare(Integer.parseInt(splitedA[0]), Integer.parseInt(splitedB[0]));

            if(compareInteger != 0) {
                return compareInteger;
            }

            return Integer.compare(
                    splitedA.length > 1 ? Integer.parseInt(splitedA[1]) : -1,
                    splitedB.length > 1 ? Integer.parseInt(splitedB[1]) : -1
            );
        });
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> nums = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            nums.add(br.readLine());
        }

        sort(nums);

        for(int i = 0; i < N; i++) {
            System.out.println(nums.get(i));
        }


    }
}