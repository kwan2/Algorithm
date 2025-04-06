import javax.management.MBeanRegistration;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author : jung-kwanhee
 * @description : 백준 - 새로운 게임
 * @packageName : PACKAGE_NAME
 * @fileName : BOJ_17780
 * @date : 25. 4. 6.
 */
public class BOJ_17780 {

    private static int N, K;
    private static Map[][] map;
    private static Player[] players;
    // 방향
    enum DIR {
        UP(new int[]{-1, 0}),
        DOWN( new int[]{1, 0}),
        RIGHT( new int[]{0, 1}),
        LEFT( new int[]{0, -1});

        private int[] dir;
        DIR(int[] dir) {
            this.dir = dir;
        }

        public int[] getDir() {
            return dir;
        }
    }

    // 말의 객체
    static class Player {
        int index;
        int x, y;
        DIR direction;

        Player(int index,int x, int y, DIR direction) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    static class Map {
        int type;
        List<Player> players;
        Map(int type) {
            this.type = type;
            players = new LinkedList<>();
        }

    }

    private static boolean isAccessible (int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    public static int solve() {

        boolean breakFlag = false;
        int turn = 0;
        while(!breakFlag) {
            turn++;
            if(turn > 1000) {
                break;
            }

            for(int k = 1; k <= K; k++) {
                int currentX = players[k].x;
                int currentY = players[k].y;

                // 1. 가장 맨 밑에 있는 player 이냐의 체크
                if(map[currentX][currentY].players.get(0).index != players[k].index) {
                    continue;
                }

                int nextX = players[k].x + players[k].direction.dir[0];
                int nextY = players[k].y + players[k].direction.dir[1];

                if(!isAccessible(nextX, nextY) || map[nextX][nextY].type == 2) {
                    // 방향 바꿈
                    switch (players[k].direction)  {
                        case UP:
                            players[k].direction = DIR.DOWN;
                            break;
                        case DOWN:
                            players[k].direction = DIR.UP;
                            break;
                        case RIGHT:
                            players[k].direction = DIR.LEFT;
                            break;
                        case LEFT:
                            players[k].direction = DIR.RIGHT;
                            break;
                    }

                    nextX = currentX + players[k].direction.dir[0];
                    nextY = currentY + players[k].direction.dir[1];

                }

                // 파란색이라 방향을 바꿨는데도 그대로라면 방향만 바꿈.
                if(!isAccessible(nextX, nextY) || map[nextX][nextY].type == 2) {
                    continue;
                } else if(map[nextX][nextY].type == 1) {
                    // 빨강
                    for(int i = map[currentX][currentY].players.size() - 1; i >= 0; i--) {
                        Player swap = map[currentX][currentY].players.get(i);
                        map[nextX][nextY].players.add(swap);
                        players[swap.index].x = nextX;
                        players[swap.index].y = nextY;
                    }
                    map[currentX][currentY].players.clear();
                } else {
                    for(int i = 0; i< map[currentX][currentY].players.size(); i++) {
                        Player swap = map[currentX][currentY].players.get(i);
                        map[nextX][nextY].players.add(swap);
                        players[swap.index].x = nextX;
                        players[swap.index].y = nextY;
                    }
                    map[currentX][currentY].players.clear();
                }

                if(map[nextX][nextY].players.size() >= 4) {
                    breakFlag = true;
                    break;
                }
            }
        }

        if(breakFlag) {
            return turn;
        } else {
            return -1;
        }

    }

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Map[N][N];
        players = new Player[K + 1];

        // 0 => 흰색
        // 1 => 빨간색
        // 2 => 파란색
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                map[i][j] = new Map(Integer.parseInt(st.nextToken()));
            }
        }

        for(int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            switch (dir) {
                case 1:
                    Player player1 = new Player(i, x, y, DIR.RIGHT);
                    map[x][y].players.add(player1);
                    players[i] = player1;
                    break;
                case 2:
                    Player player2 = new Player(i, x, y, DIR.LEFT);
                    map[x][y].players.add(player2);
                    players[i] = player2;
                    break;
                case 3:
                    Player player3 = new Player(i, x, y,DIR.UP);
                    map[x][y].players.add(player3);
                    players[i] = player3;
                    break;
                case 4:
                    Player player = new Player(i, x, y,DIR.DOWN);
                    map[x][y].players.add(player);
                    players[i] = player;
                    break;

            }
        }

        System.out.println(solve());


    }

}
