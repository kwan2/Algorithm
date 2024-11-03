import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2583 {

    static int[][] map;
    static List<Integer> areaList;
    static int area;
    static final int[] dx = {0,0,1,-1};
    static final int[] dy = {1,-1,0,0};

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, K;

    private static void solution(Point start) {
        // 1. 지나간 정보 1로 마킹
        map[start.x][start.y] = 1;

        // 2. 해당 넓이 칸 수 더하기
        area += 1;
        // 3. 다음 이동 가능 여부 체크 후, dfs
        for(int k = 0; k < 4; k++) {
            int x = start.x + dx[k];
            int y = start.y + dy[k];

            if(isAccessible(new Point(x,y)) && map[x][y] == 0) {
                solution(new Point(x,y));
            }
        }

    }

    private static boolean isAccessible(Point point) {
        return point.x >= 0 && point.x < N && point.y >= 0 && point.y < M;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        areaList = new ArrayList<>();

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            Point lp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            Point rp = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            for(int j = lp.y; j < rp.y; j++) {
                for(int k = lp.x; k < rp.x; k++) {
                    map[j][k] = 1;
                }
            }

        }


        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(map[i][j] == 0) {
                    area = 0;
                    solution(new Point(i, j));
                    areaList.add(area);
                }
            }
        }

        Collections.sort(areaList);

        System.out.println(areaList.size());
        for (Integer area : areaList) {
            System.out.print(area + " ");
        }




    }
}
