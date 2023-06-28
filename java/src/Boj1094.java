import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1094 {
    private static void divide(int X){
       int stick = 64;
       int cnt = 1;

       while(stick != X){
           if(stick > X){
               stick /= 2;
           }
           else{
               cnt++;
               X -= stick;
           }
       }
        System.out.println(cnt);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        divide(X);

    }
}
