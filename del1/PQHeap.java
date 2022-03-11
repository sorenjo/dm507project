import java.util.ArrayList;

public class PQHeap implements PQ{
    private ArrayList<Element> heap;

    /*
     * Creates a new empty priority queue.
     */
    public PQHeap() {
        heap = new ArrayList<>();
    }

    /*
     * Ensure heap order for element at index i.
     * Precondition: i < heap.size() and subheap of i is in heap order.
     */
    private void heapify(int i) {
        int l = 2*i + 1;
        int r = 2*i + 2;
        int smallest;
        if (l < heap.size() && heap.get(l).getKey() < heap.get(i).getKey())
            smallest = l;
        else
            smallest = i;
        if (r < heap.size() && heap.get(r).getKey() < heap.get(smallest).getKey())
            smallest = r;
        if (smallest != i) {
            exchange(i, smallest);
            heapify(smallest);
        }
    }

    /*
     * Exchange elements at indices i and j.
     * Precondition: i < heap.size() and j < heap.size()
     */
    private void exchange(int i, int j){
        Element temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /*
     * Removes least element of this queue and returns it.
     * Precondition: heap is not empty.
     */
    public Element extractMin(){
        Element min = heap.get(0);
        heap.set(0, heap.get(heap.size()-1));
        heap.remove(heap.size()-1);
        heapify(0);
        return min;
    }

    /*
     * Insert an element to this priority queue.
     */
    public void insert(Element e){
        heap.add(e);
        int i = heap.size()-1;
        int parent = (i-1)/2;
        //assigns parent in guard for entire loop iteration.
        while (i > 0 && heap.get(parent).getKey() > heap.get(i).getKey()) {
            exchange(i, parent);
            i = parent;
            parent = (i-1)/2;
        }
    }
}
