import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 - 로봇 청소기
 * 모두 청소하는데의 이동횟수의 최솟값 구하기
 */
public class BOJ_4991 {

    private static int answer = Integer.MAX_VALUE;
    private static boolean[] visitedNode;
    private static final int[]dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    static class Point {
        int x, y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Node {
        Point node;
        int weight;
        int index;
        Node(Point node, int weight, int index) {
            this.node = node;
            this.weight = weight;
            this.index = index;
        }
    }


    private static int computeWeight(Point startPoint, Point destPoint, String[][] map, int W, int H) {
        Queue<Node> queue = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];

        queue.add(new Node(startPoint, 0, -1));

        visited[startPoint.x][startPoint.y] = true;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int x = node.node.x, y = node.node.y;
            int weight = node.weight;

            if(x == destPoint.x && y == destPoint.y) {
                return weight;
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < H && nx >= 0 && ny < W && ny >= 0 && !visited[nx][ny] && !map[nx][ny].equals("x")) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(new Point(nx, ny), weight + 1, - 1));
                }
            }
        }

        return -1;
    }

    // 0 ~ N (모든 곳을 순회할 것)
    private static void findAnswer (int start, int depth, List<List<Node>> graph, int sum , int end) {

        if(depth == end) {
            answer = Math.min(answer, sum);
            return;
        }

        for(int i = 0; i < graph.get(start).size(); i++) {
            if(visitedNode[graph.get(start).get(i).index]) {
                continue;
            }
            visitedNode[graph.get(start).get(i).index] = true;
            findAnswer(graph.get(start).get(i).index, depth + 1, graph, sum + graph.get(start).get(i).weight , end);
            visitedNode[graph.get(start).get(i).index] = false;

        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            answer = Integer.MAX_VALUE;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            // 탐색 불가능한 Input
            if(W + H == 0 ) {
                break;
            }

            String[][] map = new String[H][W];
            List<Point> points = new LinkedList<>();
            Point startPoint = new Point(-1, -1);

            for (int i = 0; i < H; i++) {
                String widthText = br.readLine();
                for (int j = 0; j < W; j++) {
                    map[i][j] = widthText.substring(j, j + 1);
                    if(map[i][j].equals("o")) {
                        startPoint = new Point(i, j);
                    } else if (map[i][j].equals("*")) {
                        points.add(new Point(i, j));
                    }
                }
            }

            if(points.isEmpty() || startPoint.x == -1 || startPoint.y == -1) {
                break;
            }

            List<Point> tempList = new ArrayList<>();

            tempList.add(startPoint);

            tempList.addAll(points);

            points = tempList;

            // 1. 인접 리스트로 시작점과 더러운 곳의 거리를 구현
            // 1-1. 인접 리스트 초기화
            List<List<Node>> graph = new ArrayList<>();

            // 시작점 + 더러운 곳
            for(int i = 0; i < points.size(); i++) {
                graph.add(new ArrayList<>());
            }

            // 1-2. 인접 리스트 완성
            for(int i = 0; i < points.size(); i++) {
                for(int j = i + 1; j < points.size(); j++) {
                    int weight = computeWeight(points.get(i), points.get(j), map, W, H);
                    if(weight == -1) {
                        continue;
                    }
                    graph.get(i).add(new Node(points.get(j), weight, j));
                    graph.get(j).add(new Node(points.get(i), weight, i));
                }
            }

            visitedNode = new boolean[points.size()];
            visitedNode[0] = true;
            // 2. 인접리스트를 탐색하여 모든 점들을 순회하였을 때의 최소값 구하기
            findAnswer(0, 0, graph, 0, points.size() - 1);

            System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
        }
    }
}
