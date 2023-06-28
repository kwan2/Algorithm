import java.util.*;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList <Integer> answer = new ArrayList<>();
        int cnt = arr.length-1;
        
        while(cnt >= 0){
            if((arr[cnt]% divisor) == 0){
                answer.add(arr[cnt]);
            }
            cnt--;
        }
        if(answer.isEmpty()){
            answer.add(-1);
        }
        else{
            Collections.sort(answer);
        }
       int[] result = answer.stream()
	        .mapToInt(Integer::intValue)
    	    .toArray();
        return result;
    }
}