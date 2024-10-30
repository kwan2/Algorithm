import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1005 {

    private static int[] D, answer, degree;
    private static ArrayList<ArrayList<Integer>> graph;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            D = new int[N + 1];
            graph = new ArrayList<>();
            degree = new int[N + 1];
            answer = new int[N + 1];
            Queue<Integer> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            for(int i = 0; i <= N; i++){
                graph.add(new ArrayList<>());
            }

            for(int i = 1; i <= N; i++){
                D[i] = Integer.parseInt(st.nextToken());
            }

            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                graph.get(X).add(Y);
                degree[Y]++;
            }

            int W = Integer.parseInt(br.readLine());

            for(int i = 1; i <= N; i++){
                if(degree[i] == 0){
                    queue.offer(i);
                    answer[i] = D[i];
                }
            }

            while(!queue.isEmpty()){
                int cur = queue.poll();

                for (int i = 0; i < graph.get(cur).size(); i++){
                    int next = graph.get(cur).get(i);
                    answer[next] = Math.max(answer[cur] + D[next], answer[next]);
                    degree[next]--;
                    if(degree[next] == 0)
                        queue.offer(next);
                }
            }
            System.out.println(answer[W]);
        }
    }
}