import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description : 미로 탐색
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_2178
 * @date : 25. 4. 24.
 */
public class BOJ_2178 {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    private static boolean isMoveable(int x, int y, int N, int M) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int answer = Integer.MAX_VALUE;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String row = st.nextToken();
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(row.substring(j, j + 1));
            }
        }

        Queue<Integer[]> queue = new LinkedList<>();

        queue.offer(new Integer[]{0, 0, 0});

        while(!queue.isEmpty()) {
            Integer[] current = queue.poll();

            if(current[0] == N - 1 && current[1] == M - 1) {
                answer = Math.min(answer, current[2] + 1);
                break;
            }

            visited[current[0]][current[1]] = true;

            for(int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if(!isMoveable(nx, ny, N, M) || visited[nx][ny]) {
                    continue;
                }

                if(map[nx][ny] == 1) {
                    queue.offer(new Integer[]{nx, ny, current[2] + 1});
                    visited[nx][ny] = true;
                }
            }

        }

        System.out.println(answer);

    }
}
