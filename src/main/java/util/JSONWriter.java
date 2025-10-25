package main.java.util;

import com.google.gson.*;
import main.java.model.Edge;
import java.io.FileWriter;
import java.util.*;

public class JSONWriter {

    public static void write(List<Map<String, Object>> results, String filePath) throws Exception {
        JsonArray resultArray = new JsonArray();

        for (Map<String, Object> res : results) {
            JsonObject obj = new JsonObject();
            obj.addProperty("graph_id", (Integer) res.get("graph_id"));

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", (Integer) res.get("vertices"));
            inputStats.addProperty("edges", (Integer) res.get("edges"));
            obj.add("input_stats", inputStats);

            obj.add("prim", createAlgorithmResult((Map<String, Object>) res.get("prim")));
            obj.add("kruskal", createAlgorithmResult((Map<String, Object>) res.get("kruskal")));

            resultArray.add(obj);
        }

        JsonObject root = new JsonObject();
        root.add("results", resultArray);

        try (FileWriter writer = new FileWriter(filePath)) {
            new GsonBuilder().setPrettyPrinting().create().toJson(root, writer);
        }
    }

    private static JsonObject createAlgorithmResult(Map<String, Object> data) {
        JsonObject algo = new JsonObject();
        JsonArray edgesArr = new JsonArray();

        for (Edge e : (List<Edge>) data.get("mst_edges")) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", e.from);
            edgeObj.addProperty("to", e.to);
            edgeObj.addProperty("weight", e.weight);
            edgesArr.add(edgeObj);
        }

        algo.add("mst_edges", edgesArr);
        algo.addProperty("total_cost", (Integer) data.get("total_cost"));
        algo.addProperty("operations_count", (Integer) data.get("operations_count"));
        algo.addProperty("execution_time_ms", (Long) data.get("execution_time_ms"));
        return algo;
    }
}
