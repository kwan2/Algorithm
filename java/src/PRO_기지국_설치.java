/**
 * @author : jung-kwanhee
 * @description : 프로그래머스 - 기지국 설치
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_기지국_설치
 * @date : 25. 5. 5.
 */
public class PRO_기지국_설치 {
    public static int solution(int n, int[] stations, int w) {
        int answer = 0, start = 1, curIdx = 0;
        while(start <= n) {

            // station 구간 안에 없는 케이스
            // 기지국 시점으로 변경해준다.
            if(curIdx < stations.length && start >= stations[curIdx] - w) {
                start = stations[curIdx] + w + 1;
                curIdx++;
            } else {
                // 구간 안에 있는 경우 => 새로운 기지국을 설치한다.
                // 아파트를 w 구간 두배 만큼 간 후 + 1
                start += (2 * w) + 1;
                answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(11, new int[] {4, 11},  1));
    }
}
