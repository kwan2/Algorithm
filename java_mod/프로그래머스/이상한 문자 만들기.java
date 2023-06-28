class Solution {
    public String solution(String s) {
        String answer = "";
        int cnt = 0;
        String[] temp = s.split("");
        
        for(int i =0; i<temp.length;i++){
            if(temp[i].equals(" ")){
                answer+= " ";
                cnt = 0;
            }else if(cnt % 2 ==0){
                answer += temp[i].toUpperCase();
                cnt++;
            }
            else if(cnt % 2 ==1){
                answer += temp[i].toLowerCase();
                cnt++;
            }
        }
        return answer;
    }
}