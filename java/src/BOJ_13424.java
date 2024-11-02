import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13424 {

    static List<Integer> friends;
    static List<Node>[] graph;
    static int N, M, K;
    static int[][] dist;
    static int[] answer;

    static class Node {
        int v, w;
        Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    private static void dijkstra(int start) {
        // 1 부터 ~ N까지의 초합 거리를 구함
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        boolean[] visited = new boolean[N + 1];

        pq.offer(new Node(start, 0));
        visited[start] = true;

        while(!pq.isEmpty()) {
            Node current = pq.poll();

            if(current.w > dist[start][current.v]) continue;

            for(Node next : graph[current.v]) {
                int tempDist = dist[start][current.v] + next.w;
                if(tempDist < dist[start][next.v]) {
                    dist[start][next.v] = tempDist;
                    pq.offer(new Node(next.v, tempDist));
                }
            }
        }


    }

    private static void floydWarshall() {
        for(int i = 1; i <= N; i++) {
            dijkstra(i);
        }
    }

    private static void solution() {
        for(int i = 1; i <= N; i++) {
            int sum = 0;
            for(Integer friend : friends) {
                 sum += dist[i][friend];
            }
            if(answer[1] > sum) {
                answer[0] = i;
                answer[1] = sum;
            }

        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // N과 M input 받기
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // graph 및 친구들 리스트
            graph = new ArrayList[N + 1];
            dist = new int[N + 1][N + 1];

            for(int i = 1; i <= N; i++) {
                Arrays.fill(dist[i], Integer.MAX_VALUE);
                dist[i][i] = 0;
                graph[i] = new ArrayList<>();
            }



            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph[a].add(new Node(b, c));
                graph[b].add(new Node(a, c));
            }

            floydWarshall();


            answer = new int[2];
            answer[1] = Integer.MAX_VALUE;
            friends = new ArrayList<>();

            K = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            for(int i = 0; i < K; i++) {
                friends.add(Integer.parseInt(st.nextToken()));
            }


            solution();

            System.out.println(answer[0]);
        }


    }
}
