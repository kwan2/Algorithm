import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        
        Stack <Integer> stack = new Stack<>();
        for(int i = 0; i<arr.length;i++){
            if (stack.empty())
                stack.add(arr[i]);
            else if(stack.peek() != arr[i])
                stack.push(arr[i]);
        }
        int[] answer = new int[stack.size()];
        int cnt = stack.size() -1 ;
        while(stack.empty() != true)
            answer[cnt--] = stack.pop();
        return answer;
    }
}