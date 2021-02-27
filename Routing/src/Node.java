import java.util.ArrayList;
import java.util.Objects;

public class Node {

    private double longitude;
    private double latitude;
    private String identification;
    private boolean explored;
    private double cost;
    private ArrayList<Node> adjacencyList;
    private ArrayList<Edge> edges;
    private Node prev;

    public Node(double longitude, double latitude, String identification) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.identification = identification;
        this.explored = false;
        this.cost = Double.MAX_VALUE;
        this.adjacencyList = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.prev = null;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getIdentification() {
        return identification;
    }

    public boolean isExplored() {
        return explored;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double distance) {
        this.cost = distance;
    }

    public ArrayList<Node> getAdjacencyList() {
        return adjacencyList;
    }

    public void addAdjacencyList(Node node) {
        this.adjacencyList.add(node);
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public void addEge(Edge edge) {
        this.edges.add(edge);
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getPrev() {
        return prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Double.compare(node.getLongitude(), getLongitude()) == 0 &&
                Double.compare(node.getLatitude(), getLatitude()) == 0 &&
                isExplored() == node.isExplored() &&
                Double.compare(node.getCost(), getCost()) == 0 &&
                getIdentification().equals(node.getIdentification()) &&
                getAdjacencyList().equals(node.getAdjacencyList()) &&
                getEdges().equals(node.getEdges()) &&
                getPrev().equals(node.getPrev());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude(), getIdentification(), isExplored(), getCost(), getAdjacencyList(), getEdges(), getPrev());
    }
}
