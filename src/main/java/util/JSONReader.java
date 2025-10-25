package main.java.util;

import com.google.gson.*;
import main.java.model.Edge;
import main.java.model.Graph;
import java.io.FileReader;
import java.util.*;

public class JSONReader {
    public static List<Graph> read(String filePath) throws Exception {
        List<Graph> graphs = new ArrayList<>();
        JsonObject json = JsonParser.parseReader(new FileReader(filePath)).getAsJsonObject();
        JsonArray arr = json.getAsJsonArray("graphs");

        for (JsonElement el : arr) {
            JsonObject obj = el.getAsJsonObject();
            Graph g = new Graph(obj.get("id").getAsInt());

            for (JsonElement n : obj.getAsJsonArray("nodes"))
                g.nodes.add(n.getAsString());

            for (JsonElement e : obj.getAsJsonArray("edges")) {
                JsonObject edgeObj = e.getAsJsonObject();
                g.edges.add(new Edge(
                        edgeObj.get("from").getAsString(),
                        edgeObj.get("to").getAsString(),
                        edgeObj.get("weight").getAsInt()
                ));
            }
            graphs.add(g);
        }
        return graphs;
    }
}
