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
 * @fileName : BOJ_13460
 * @date : 25. 12. 04.
 */
public class BOJ_13460 {

    private static Position red, blue, target;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    private static class Beed {
        Position redPosition, bluePosition;
        int count;
        Beed (Position redPosition, Position bluePosition, int count) {
            this.redPosition = redPosition;
            this.bluePosition = bluePosition;
            this.count = count;
        }
    }

    private static class Position {
        int x, y;
        Position (int x, int y) {
            this.x = x;
            this.y = y;
        }
        boolean isEqual (Position other) {
            return x == other.x && y == other.y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String[][] maps = new String[N][M];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                maps[i][j] = line.charAt(j) + "";

                if("R".equals(maps[i][j])) {
                    red = new Position(i, j);
                } else if ("B".equals(maps[i][j])) {
                    blue = new Position(i, j);
                } else if ("O".equals(maps[i][j])) {
                    target = new Position(i, j);
                }
            }
        }

        int totalCount = simulation(maps, 10, N, M);

        System.out.println(totalCount);

    }

    private static boolean isAccessAble (int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static int simulation (String[][] maps, int limitCount, int n, int m) {
        // 동시에 움직인다.
        Queue<Beed> queue = new LinkedList<>();
        boolean[][][][] isVisited = new boolean[n][m][n][m];
        queue.offer(new Beed(red, blue, 1));

        isVisited[red.x][red.y][blue.x][blue.y] = true;

        while(!queue.isEmpty()) {
            Beed beed = queue.poll();
            int rx = beed.redPosition.x;
            int ry = beed.redPosition.y;
            int bx = beed.bluePosition.x;
            int by = beed.bluePosition.y;

            if(beed.count > limitCount) {
                return -1;
            }

            for(int i = 0; i < 4; i++) {
                Position nextRed = new Position(beed.redPosition.x, beed.redPosition.y);
                Position nextBlue = new Position(beed.bluePosition.x, beed.bluePosition.y);

                boolean isRedReached = false;
                boolean isBlueReached = false;
                // 내부 시뮬레이션
                // 동시에 같은 같은 방향으로 이동
                while(isAccessAble(nextRed.x + dx[i], nextRed.y + dy[i], n, m)
                        && !maps[nextRed.x + dx[i]][nextRed.y + dy[i]].equals("#")) {
                    nextRed.x += dx[i];
                    nextRed.y += dy[i];

                    if(target.isEqual(nextRed)) {
                        isRedReached = true;
                        break;
                    }
                }

                while(isAccessAble(nextBlue.x + dx[i], nextBlue.y + dy[i], n, m)
                        && !maps[nextBlue.x + dx[i]][nextBlue.y + dy[i]].equals("#")) {
                    nextBlue.x += dx[i];
                    nextBlue.y += dy[i];

                    if(target.isEqual(nextBlue)) {
                        isBlueReached = true;
                        break;
                    }
                }

                if(isBlueReached) {
                    continue;
                }

                if(isRedReached) {
                    return beed.count;
                }
                if (nextRed.isEqual(nextBlue)) {
                    switch (i) {
                        case 0: // 위로 이동 (dx = -1)
                            if (beed.redPosition.x > beed.bluePosition.x) {
                                nextRed.x -= dx[i];   // -(-1) = +1
                            } else {
                                nextBlue.x -= dx[i];
                            }
                            break;

                        case 1: // 아래로 이동 (dx = +1)
                            if (beed.redPosition.x < beed.bluePosition.x) {
                                nextRed.x -= dx[i];   // -(+1) = -1
                            } else {
                                nextBlue.x -= dx[i];
                            }
                            break;

                        case 2: // 좌측으로 이동 (dy = -1)
                            if (beed.redPosition.y > beed.bluePosition.y) {
                                nextRed.y -= dy[i];   // -(-1) = +1
                            } else {
                                nextBlue.y -= dy[i];
                            }
                            break;

                        case 3: // 우측으로 이동 (dy = +1)
                            if (beed.redPosition.y < beed.bluePosition.y) {
                                nextRed.y -= dy[i];   // -(+1) = -1
                            } else {
                                nextBlue.y -= dy[i];
                            }
                            break;
                    }
                }


                if(!isVisited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) {
                    isVisited[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
                    queue.offer(new Beed(nextRed, nextBlue, beed.count + 1));
                }

            }
        }
        return -1;

    }
}
