import java.util.*;
class Solution {
    public int solution(int n) {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        // 2-n의 값 모두 arr에 추가.
        for(int i =2; i<=n; i++){
            map.put(i,i);
        }
        // 에라토스테네스의 체
        for(int i=2; i<=n;i++){
            for(int j = 2*i; j<=n ; j += i){
                map.remove(j);
            }
        }
        return map.size();
    }
}