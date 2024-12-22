import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 초기 접근법은 수평 이동 우선으로 진행 후, 수직으로 이동하는 것으로 구현했었으나 계속 돌아가는 메모리 문제가 발생하였음.
 * 이에 따라서, 다른분의 풀이를 참고해서 초기 접근 시 수직 수평에 대항 이동을 둘 다 진행하도록 넣어준 이후 구현 완료 !!
 */
public class BOJ_6087 {

    // 'C'로 표시되어 있는 두 칸을 레이저로 통신하기 위해서 설치해야 하는 거울 개수의 최솟값
    private static int answer;
    private static final int[] DX = {-1, 1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};
    private static class Point {
        int x;
        int y;
        int mirrorCount;
        boolean isHorizontal;
        Point(int x, int y, int mirrorCount, boolean isHorizontal) {
            this.x = x;
            this.y = y;
            this.mirrorCount = mirrorCount;
            this.isHorizontal = isHorizontal;
        }

    }

    private static boolean isNotAccessible(int x, int y, int W, int H) {
        return x < 0 || x >= H || y < 0 || y >= W;
    }


    private static void bfs(Point startPoint, Point endPoint, int W, int H, String[][] map) {
        Queue<Point> queue = new LinkedList<>();

        boolean[][][] visited = new boolean[H][W][2];

        visited[startPoint.x][startPoint.y][0] = true;
        visited[startPoint.x][startPoint.y][1] = true;

        queue.offer(startPoint);
        queue.offer(new Point(startPoint.x, startPoint.y, startPoint.mirrorCount, !startPoint.isHorizontal));
        while(!queue.isEmpty()) {
            Point point = queue.poll();

            int x = point.x;
            int y = point.y;
            int mirrorCount = point.mirrorCount;
            boolean isHorizontal = point.isHorizontal;

            if(endPoint.x == x && endPoint.y == y) {
                answer = mirrorCount - 1;
                return;
            }


            // 수평 이동
            if(isHorizontal) {

                // 한쪽으로 계속 이동
                for(int i = 2; i < 4; i++) {
                    int nx = x + DX[i];
                    int ny = y + DY[i];

                    while(true){
                        if(isNotAccessible(nx, ny, W, H)){
                            break;
                        }

                        if(map[nx][ny].equals("*")){
                            break;
                        }

                        if(visited[nx][ny][0]) {
                            break;
                        }

                        visited[nx][ny][0] = true;

                        queue.offer(new Point(nx, ny, mirrorCount + 1, false));
                        nx = nx + DX[i];
                        ny = ny + DY[i];
                    }
                }
            } else {
                for(int i = 0; i < 2; i++){
                    int nx = x + DX[i];
                    int ny = y + DY[i];

                    while(true){
                        if(isNotAccessible(nx, ny, W, H)){
                            break;
                        }

                        if(map[nx][ny].equals("*")){
                            break;
                        }

                        if(visited[nx][ny][1]) {
                            break;
                        }
                        visited[nx][ny][1] = true;

                        queue.offer(new Point(nx, ny, mirrorCount + 1, true ));

                        nx = nx + DX[i];
                        ny = ny + DY[i];
                    }
                }

            }

        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // W x H 의 Map
        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        String[][] map = new String[H][W];
        List<Point> razerPoints = new ArrayList<>();

        for(int i = 0; i < H; i++) {
            String widthText = br.readLine();
            for(int j = 0; j < W; j++) {
                map[i][j] = widthText.substring(j, j + 1);
                if(map[i][j].equals("C")) {
                    razerPoints.add(new Point(i, j, 0, true));
                }
            }
        }

        answer = Integer.MAX_VALUE;

        Point startPoint = razerPoints.get(0);
        Point endPoint = razerPoints.get(1);


        bfs(startPoint, endPoint, W, H, map);
        System.out.println(answer);
    }
}
