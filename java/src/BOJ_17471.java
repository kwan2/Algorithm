import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ_17471 {

    static int N, answer;
    static int[] area;
    static List<List<Integer>> graph;
    static boolean[] isVisited;

    private static void backTracking(int index) {

        if(index == N + 1) {
            List<Integer> groupA = new ArrayList<>(), groupB = new ArrayList<>();

            for(int i = 1; i <= N; i++) {
                if(isVisited[i]) {
                    groupA.add(i);
                } else {
                    groupB.add(i);
                }
            }
            // 1. 두 선거구로 올바르게 분할이 되어 있는지 확인
            if(isDivided(groupA) && isDivided(groupB)){
                int sumA = groupA.stream().mapToInt(idx -> area[idx]).sum();
                int sumB = groupB.stream().mapToInt(idx -> area[idx]).sum();

                answer = Math.min(answer, Math.abs(sumA - sumB));
            }

            return;

        }

        isVisited[index] = true;
        backTracking(index + 1);
        isVisited[index] = false;
        backTracking(index + 1);
    }

    private static boolean isDivided (List<Integer> group) {

        // 하나에 몰빵된 사례임
        if(group.isEmpty()) {
            return false;
        }

        Queue<Integer> queue = new LinkedList<>();

        Map<Integer,Boolean> isConnectMap = group.stream()
                .collect(Collectors.toMap(key->key, value -> false));

        queue.add(group.get(0));
        isConnectMap.put(group.get(0),true);

        while(!queue.isEmpty()) {
            int current = queue.poll();

            for(Integer next : graph.get(current)) {

                if(isConnectMap.containsKey(next) && !isConnectMap.get(next)) {
                    queue.add(next);
                    isConnectMap.put(next, true);
                }
            }
        }

        return isConnectMap.values().stream().allMatch(value-> value);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        area = new int[N + 1];
        graph = new ArrayList<>();

        for(int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            area[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            int count = Integer.parseInt(st.nextToken());

            for(int j = 0; j < count; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        answer = Integer.MAX_VALUE;
        isVisited = new boolean[N + 1];

        backTracking(1);

        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}