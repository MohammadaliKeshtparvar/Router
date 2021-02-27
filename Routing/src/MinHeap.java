import java.util.HashMap;

public class MinHeap {
    private Node[] Heap;
    private int size;
    private int maxsize;
    private static final int FRONT = 1;
    private HashMap<String, Integer> findingIndex;
    
    public MinHeap(int maxsize) {
        this.maxsize = maxsize;
        this.size = 0;
        findingIndex = new HashMap<>();
        Heap = new Node[this.maxsize + 1];
        Heap[0] = new Node(0, 0, "0");
        Heap[0].setCost(Integer.MIN_VALUE);
    }

    public Node[] getHeap() {
        return Heap;
    }

    private int parent(int pos)
    {
        return pos / 2;
    }
    
    private int leftChild(int pos) {
        return (2 * pos);
    }
    
    private int rightChild(int pos) {
        return (2 * pos) + 1;
    }
    
    private boolean isLeaf(int pos) {
        return pos > (size / 2);
    }
    
    private void swap(int prev, int next) {
        findingIndex.remove(Heap[prev].getIdentification());
        findingIndex.remove(Heap[next].getIdentification());
        findingIndex.put(Heap[prev].getIdentification(), next);
        findingIndex.put(Heap[next].getIdentification(), prev);
        Node tmp;
        tmp = Heap[prev];
        Heap[prev] = Heap[next];
        Heap[next] = tmp;
    }

    public void minHeapify(int pos) {
//        if (!isLeaf(pos)) {
//            try {
//                if (Heap[pos].getCost() > Heap[leftChild(pos)].getCost() || Heap[pos].getCost() > Heap[rightChild(pos)].getCost()) {
//                    if (Heap[leftChild(pos)].getCost() < Heap[rightChild(pos)].getCost()) {
//                        swap(pos, leftChild(pos));
//                        minHeapify(leftChild(pos));
//                    } else {
//                        swap(pos, rightChild(pos));
//                        minHeapify(rightChild(pos));
//                    }
//                }
//            }catch (ArrayIndexOutOfBoundsException e) {
//            }
//        }
        int left = leftChild(pos);
        int right = rightChild(pos);
        int largest = pos;
        if(left <= size && Heap[left].getCost() < Heap[largest].getCost())
            largest=left;
        if(right <= size && Heap[right].getCost() < Heap[largest].getCost())
            largest = right;
        if(largest != pos) {
            swap(pos, largest);
            minHeapify(largest);
        }
    }

    public void insert(Node element) {
        if (size >= maxsize) {
            return;
        }
        Heap[++size] = element;
        int current = size;
        findingIndex.put(element.getIdentification(), size);
        while (Heap[current].getCost() < Heap[parent(current)].getCost()) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public void minHeap() {
        for (int pos = (size / 2) ; pos >= 1; pos--) {
            minHeapify(pos);
        }
    }

    public Node remove() {
        if (size <= 0)
            return null;
        findingIndex.remove(Heap[FRONT].getIdentification());
        Node popped = Heap[FRONT];
        Heap[FRONT] = Heap[size--];
        findingIndex.put(Heap[FRONT].getIdentification(), FRONT);
        minHeapify(FRONT);
        return popped;
    }

    public HashMap<String, Integer> getFindingIndex() {
        return findingIndex;
    }
}