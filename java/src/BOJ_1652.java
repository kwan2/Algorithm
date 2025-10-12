import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author : jung-kwanhee
 * @description :
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_1652
 * @date : 25. 10. 12.
 */
public class BOJ_1652 {

    private static String[][] map;

    private static boolean isNotAccess (int x, int y, int N) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }

    private static int bfs (int N, int dir) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[] {0, 0});

        if(dir == 1) {
            // 가로 방향
            while(!queue.isEmpty()) {
                int[] pos = queue.poll();
                int x = pos[0], y = pos[1];

                if(x >= N) {
                    break;
                }
                for(int i = 0; i < N - y + 1; i++) {
                    int ny = y + i;

                    if(isNotAccess(x, ny, N)) {
                        queue.offer(new int[] {x + 1, 0});
                        if(ny - y >= 2) {
                            answer++;
                        }
                        break;
                    }

                    if(map[x][ny].equals("X")) {
                        if(ny - y >= 2) {
                            answer++;
                        }
                        // 벽 너머에 위치값을 조정한다.

                        boolean isAccess = true;

                        while(map[x][ny].equals("X")) {

                            if(isNotAccess(x, ny + 1, N)) {
                                isAccess = false;
                                break;
                            }

                            ny++;
                        }
                        if(isAccess) {
                            queue.offer(new int[] {x, ny});
                        } else {
                            queue.offer(new int[] {x + 1, 0});
                        }


                        break;
                    }

                }
            }
        } else {

            while(!queue.isEmpty()) {
                int[] pos = queue.poll();
                int x = pos[0], y = pos[1];

                if(y >= N) {
                    break;
                }
                for(int i = 0; i < N - x + 1; i++) {
                    int nx = x + i;
                    if(isNotAccess(nx, y, N)) {
                        queue.offer(new int[] {0, y + 1});
                        if(nx - x >= 2) {
                            answer++;
                        }
                        break;
                    }

                    if(map[nx][y].equals("X")) {
                        if(nx - x >= 2) {
                            answer++;
                        }
                        // 벽 너머에 위치값을 조정한다.

                        boolean isAccess = true;

                        while(map[nx][y].equals("X")) {

                            if(isNotAccess(nx + 1, y, N)) {
                                isAccess = false;
                                break;
                            }

                            nx++;
                        }
                        if(isAccess) {
                            queue.offer(new int[] {nx, y});
                        } else {
                            queue.offer(new int[] {0, y + 1});
                        }


                        break;
                    }
                }
            }
            // 세로방향
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        map = new String[N][N];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) + "";
            }
        }

        int[] answer = new int[2];

        // 무조건 쭉 간다.

        answer[0] = bfs(N, 1);
        answer[1] = bfs(N, 2);

        System.out.println(answer[0] +" " + answer[1]);

    }
}
