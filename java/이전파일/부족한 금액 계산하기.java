class Solution {
    public long solution(int price, int money, int count) {
        long answer = 0;
        for(long i = 1; i<=count;i++){
            answer += i*price;
        }
        if(answer <= money){
            answer = 0;
        }else{
            answer -= money;
        }
            
        return answer;
    }
}