import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {

    private static class Node implements Comparable<Node>{
        int V, W;
        Node(int V, int W){
            this.V = V;
            this.W = W;
        }

        public int compareTo(Node node){
            return this.W - node.W;
        }
    }
    private static ArrayList<Node>[] graph;
    private static PriorityQueue<Node> pq;
    private static boolean[] visited;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        pq = new PriorityQueue<>();
        for(int i =1; i<= V; i++) graph[i] = new ArrayList<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }

        visited = new boolean[V + 1];
        pq.add(new Node(1,0));

        int answer = 0, cnt = 0;
        while(!pq.isEmpty()){
            Node vector = pq.poll();
            if(visited[vector.V] == true) continue;

            answer += vector.W;
            visited[vector.V] = true;
            cnt++;

            if(cnt == V) break;

            for(Node next : graph[vector.V]){
                if(visited[next.V]) continue;
                pq.add(next);
            }
        }

        System.out.println(answer);

    }
}