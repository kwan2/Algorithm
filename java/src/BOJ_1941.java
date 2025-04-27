import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : jung-kwanhee
 * @description : 소문난 칠공주
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_1941
 * @date : 25. 4. 27.
 */
public class BOJ_1941 {
    private static int N, answer;
    private static String[][] map;
    private static boolean[] visited;
    private static int[] selected;
    private static final int[] dx = { -1, 1, 0, 0};
    private static final int[] dy = { 0, 0, 1, -1};



    // depth : 뽑은 칸의 개수
    // start : 시작 인덱스
    // sCnt : 이다솜파 카운트
    private static void backTracking(int depth, int start, int sCnt) {

        if(depth == 7) {
            // 1. 이다솜파 카운트 값이 4개 이하면 그냥 리턴 시킴
            if(sCnt < 4) {
                return;
            } else {
                Arrays.fill(visited, false);

                if(bfs(selected[0] / 5, selected[0] % 5 )) {
                    answer++;
                }

                return;
            }
        }

        // 7칸을 뽑는 곳

        for(int i = start; i < N * N; i++) {
            int x = i / 5;
            int y = i % 5;
            selected[depth] = i;

            if(map[x][y].equals("S")){
                backTracking(depth + 1, i + 1, sCnt + 1);
            } else {
                backTracking(depth + 1, i + 1, sCnt);
            }
        }

    }

    private static boolean bfs(int x, int y) {
        int checkCount = 1;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[0] = true;

        while(!q.isEmpty()) {
            int[] current = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                int nextIdx = nx * N + ny;

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    continue;
                }

                for(int j = 0; j < 7; j++) {
                    // 할당했던 칸과 맞으면 칠공주파
                    if(!visited[j] && selected[j] == nextIdx) {
                        visited[j] = true;
                        q.add(new int[]{nx, ny});
                        checkCount++;
                    }
                }
            }
        }

        return checkCount == 7;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 5;
        answer = 0;
        map = new String[N][N];
        visited = new boolean[7];
        selected = new int[7];

        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) + "";
            }
        }

        backTracking(0, 0, 0);


        System.out.println(answer);
    }
}
