import java.util.ArrayList;

class Solution {
    public int solution(int n) {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        
        // 앞뒤 반전과 3진번 변환 동시
        for (int i = n; i != 0; i/=3){
            temp.add(i%3);
        }
        int result = 0;
        int cnt = 0;
        for (int i = temp.size()-1; i >= 0 ; i--){
            result += (Math.pow(3,cnt++) * temp.get(i));
        }
        return result;
    }
}