class Solution {
    public int[] solution(int n, int m) {
        
        // 큰값 : a, 작은값 : b
        int a = Math.max(n,m);
        int b = Math.min(n,m);
        // 최대공약수 : 유클리드 호제
        while(b != 0){
            int temp = a% b;
            a = b;
            b = temp;
        }
        // 최소 공배수 = n * m / 최대 공약수
        return new int[] {a, n*m /a};
    }
}