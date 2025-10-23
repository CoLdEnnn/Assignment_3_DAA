package model;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    public int id;
    public List<String> nodes = new ArrayList<>();
    public List<Edge> edges = new ArrayList<>();

    public Graph(int id) {
        this.id = id;
    }
}
