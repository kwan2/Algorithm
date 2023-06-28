class Solution {
    public int solution(String s) {
        
        String[] letter = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        
        for(int i =0; i<letter.length;i++){
            s = s.replace(letter[i],Integer.toString(i));
        }
        int answer = Integer.parseInt(s);
        return answer;
    }
}