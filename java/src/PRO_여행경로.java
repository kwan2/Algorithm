import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author : jung-kwanhee
 * @description : 여행경로
 * @packageName : PACKAGE_NAME
 * @fileName : PRO_여행경로
 * @date : 25. 5. 5.
 */
public class PRO_여행경로 {
    private static Map<String, PriorityQueue<String>> graph;

    private static void findRoute(String airport, LinkedList<String> route) {
        PriorityQueue<String> nextRoute = graph.get(airport);

        while(nextRoute != null && !nextRoute.isEmpty()) {
            String next = nextRoute.poll();
            findRoute(next, route);
        }

        route.addFirst(airport);
    }

    public static String[] solution(String[][] tickets) {

        int n = tickets.length;

        graph = new HashMap<>();

        for(String[] ticket : tickets) {
            if(!graph.containsKey(ticket[0])) {
                graph.put(ticket[0], new PriorityQueue<>());
            }

            PriorityQueue<String> current = graph.get(ticket[0]);
            current.offer(ticket[1]);

            graph.put(ticket[0],current);
        }

        LinkedList<String> route = new LinkedList<>();

        findRoute("ICN", route);

        return route.stream().toArray(String[]::new);
    }

    public static void main(String[] args) {
        String[][] tickets = {
                {"ICN", "SFO"},
                {"ICN", "ATL"},
                {"SFO", "ATL"},
                {"ATL", "ICN"},
                {"ATL", "SFO"}
        };

        String[] route = solution(tickets);

        for(int i = 0; i < route.length; i++) {
            if(i == route.length - 1) {
                System.out.print(route[i]);
            } else {
                System.out.print(route[i] + " -> ");
            }
        }


    }
}
