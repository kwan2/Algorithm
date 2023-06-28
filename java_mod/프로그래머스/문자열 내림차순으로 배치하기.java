import java.util.Arrays;
import java.util.Collections;
class Solution {
    public String solution(String s) {
        String answer= "";
        Character[] c = new Character[s.length()];
        for(int i =0; i<s.length();i++){
            c[i] = s.charAt(i);
        }
        
        Arrays.sort(c,Collections.reverseOrder());
        
        for(int i =0; i< c.length;i++){
            answer+= c[i];
        }
        return answer;
    }
}