import java.util.ArrayList;
import java.util.Objects;

public class Edge {

    private Node firstNode;
    private Node endNode;
    private ArrayList<Double> time;

    public Edge(Node firstNode, Node endNode) {
        this.firstNode = firstNode;
        this.endNode = endNode;
        this.time = new ArrayList<>();
    }

    public Node getFirstNode() {
        return firstNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public ArrayList<Double> getTime() {
        return time;
    }

    public void addTime(Double time) {
        this.time.add(time);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return getFirstNode().equals(edge.getFirstNode()) &&
                getEndNode().equals(edge.getEndNode()) &&
                getTime().equals(edge.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstNode(), getEndNode(), getTime());
    }
}
