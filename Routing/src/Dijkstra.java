import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

public class Dijkstra {
//    private ArrayList<Node> graph;
    private int nodeNumber;
    private int edgeNumber;
    private MinHeap minHeap;
    private ArrayList<Edge> edges;
    private boolean isFirst;

    public Dijkstra() {
//        this.graph = new ArrayList<>();
        this.edges = new ArrayList<>();
        this.isFirst = true;
    }

    public void mapping() {
        HashMap<String, Node> nodeID = new HashMap<>();
        File file = new File(".\\src\\4\\m4.txt");
        try {
            Scanner sc = new Scanner(file);
            if (sc.hasNextInt()) {
                String value = sc.nextLine();
                String[] data = value.split(" ");
                nodeNumber = Integer.parseInt(data[0]);
                edgeNumber = Integer.parseInt(data[1]);
                minHeap = new MinHeap(nodeNumber);
                for (int i = 0; i < nodeNumber; i++) {
                    String temp = sc.nextLine();
                    String[] nodeInfo = temp.split(" ");
                    Node node = new Node(Double.parseDouble(nodeInfo[2]), Double.parseDouble(nodeInfo[1]), nodeInfo[0]);
//                    graph.add(node);
                    minHeap.insert(node);
                    nodeID.put(nodeInfo[0], node);
                }
                for (int j = 0; j < edgeNumber; j++) {
                    String temp = sc.nextLine();
                    String[] nodeInfo = temp.split(" ");
                    nodeID.get(nodeInfo[0]).addAdjacencyList(nodeID.get(nodeInfo[1]));
                    nodeID.get(nodeInfo[1]).addAdjacencyList(nodeID.get(nodeInfo[0]));
                    Edge newEdge1 = new Edge(nodeID.get(nodeInfo[0]), nodeID.get(nodeInfo[1]));
                    Edge newEdge2 = new Edge(nodeID.get(nodeInfo[1]), nodeID.get(nodeInfo[0]));
                    nodeID.get(nodeInfo[0]).addEge(newEdge1);
                    nodeID.get(nodeInfo[1]).addEge(newEdge2);
                    if (isFirst)
                        edges.add(newEdge1);
                }
                isFirst = false;
                minHeap.minHeap();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Node> shortestRout(double requestTime, String srcID, String distID) {
        mapping();
        ArrayList<Node> rout = new ArrayList<>();
        ArrayList<Node> tree = new ArrayList<>();
        int indexSrc = minHeap.getFindingIndex().get(srcID);
        minHeap.getHeap()[indexSrc].setCost(0);
        minHeap.minHeap();
        try {
            while (!minHeap.getHeap()[minHeap.getFindingIndex().get(distID)].isExplored()) {
                Node nextNode = minHeap.remove();
                nextNode.setExplored(true);
                neighbours(requestTime, tree, rout, nextNode);
                tree.add(nextNode);
            }
        }catch (NullPointerException e) {

        }
        ArrayList<Node> idRout = new ArrayList<>();
        Node n = rout.get(rout.size() - 1);
        idRout.add(n);
        while (true) {
            n = n.getPrev();
            if (n == null)
                break;
            idRout.add(n);
        }
        for (int i = 0; i < idRout.size(); i++) {
            try {
                int index = findEdge(idRout.get(i), idRout.get(i + 1));
                if (index != -1) {
//                    edges.get(index).addTime(idRout.get(i).getCost() * 120 + requestTime);
                    edges.get(index).addTime(idRout.get(0).getCost() * 120 + requestTime);
                }
            }catch (IndexOutOfBoundsException e) {

            }
        }
        return idRout;
    }

    private void neighbours(double requestTime, ArrayList<Node> tree, ArrayList<Node> rout, Node currentNode) {
        double edgeWeight = -1;
        double newWeight = -1;
        double edgeLength;
        for (int i = 0; i < currentNode.getAdjacencyList().size(); i++) {
            Node v = currentNode.getAdjacencyList().get(i);
            if (!tree.contains(v)) {
                edgeLength = Math.pow(Math.pow(currentNode.getLatitude() - v.getLatitude(), 2) + Math.pow(currentNode.getLongitude() - v.getLongitude(), 2), 0.5);
                // Concessionary part
//                requestTime += currentNode.getCost() * 120;
                edgeWeight = edgeLength * (1 + 0.3 * findWeigh(requestTime, currentNode, v));
                newWeight = currentNode.getCost() + edgeWeight;
                try {
                    if (newWeight < v.getCost()) {
                        int index = minHeap.getFindingIndex().get(v.getIdentification());
                        minHeap.getHeap()[index].setCost(newWeight);
                        minHeap.minHeap();
                        v.setPrev(currentNode);
                    }
                }catch (NullPointerException e) {
                }
            }
        }
        rout.add(currentNode);
    }

    private int findWeigh(double requestTime, Node first, Node end) {
        for (Edge e : edges) {
            if ((e.getFirstNode().getIdentification().equals(first.getIdentification()) && e.getEndNode().getIdentification().equals(end.getIdentification())) ||
                    (e.getFirstNode().getIdentification().equals(end.getIdentification()) && e.getEndNode().getIdentification().equals(first.getIdentification()))) {
                int counter = 0;
                for (Double d : e.getTime()) {
                    if (d > requestTime) {
                        counter++;
                    }
                }
                return counter;
            }
        }
        return 0;
    }

    private int findEdge(Node first, Node end) {
        int counter = 0;
        for (Edge e : edges) {
            if ((e.getFirstNode().getIdentification().equals(first.getIdentification()) && e.getEndNode().getIdentification().equals(end.getIdentification())) ||
                    (e.getFirstNode().getIdentification().equals(end.getIdentification()) && e.getEndNode().getIdentification().equals(first.getIdentification()))) {
                return counter;
            }
            counter++;
        }
        return -1;
    }
}


