class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        // 1이 아닐 때 까지 계속 반복
        while(!s.equals("1")){
            answer[0]++;
            String c = s.replaceAll("0","");
            answer[1] += s.length() - c.length();
            s = Integer.toBinaryString(c.length()).toString();
        }
        return answer;
    }
}