class Solution {
    public long solution(long n) {
        long answer = 0;
        long cnt = 1;
        while(true){
            if((cnt * cnt) == n){
                answer = (cnt +1) * (cnt +1);
                break;
            }
            else if(cnt*cnt > n){
                answer = -1;
                break;
            }
            cnt++;
        }
        
        return answer;
    }
}