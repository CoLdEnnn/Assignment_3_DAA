package algorithms;

import model.Edge;
import model.Graph;
import java.util.*;

public class PrimAlgorithm {

    public static Map<String, Object> run(Graph graph) {
        long start = System.currentTimeMillis();
        int operations = 0;

        List<Edge> mst = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        String startNode = graph.nodes.get(0);
        visited.add(startNode);

        // Добавляем все рёбра из начальной вершины
        for (Edge e : graph.edges) {
            if (e.from.equals(startNode) || e.to.equals(startNode)) {
                pq.add(e);
                operations++;
            }
        }

        while (mst.size() < graph.nodes.size() - 1 && !pq.isEmpty()) {
            Edge edge = pq.poll();
            operations++;

            String nextNode = visited.contains(edge.from) ? edge.to : edge.from;
            if (visited.contains(nextNode)) continue;

            visited.add(nextNode);
            mst.add(edge);

            for (Edge e : graph.edges) {
                if ((e.from.equals(nextNode) && !visited.contains(e.to)) ||
                        (e.to.equals(nextNode) && !visited.contains(e.from))) {
                    pq.add(e);
                    operations++;
                }
            }
        }

        long end = System.currentTimeMillis();

        int totalCost = mst.stream().mapToInt(e -> e.weight).sum();

        Map<String, Object> result = new HashMap<>();
        result.put("mst_edges", mst);
        result.put("total_cost", totalCost);
        result.put("operations_count", operations);
        result.put("execution_time_ms", (end - start));
        return result;
    }
}
