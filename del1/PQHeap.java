import java.util.ArrayList;

public class PQHeap implements PQ{
    private ArrayList<Element> heap;

    public PQHeap(){
        heap = new ArrayList<>();
    }

    public Element extractMin(){
        int min = heap.get(0);
        heap.set(0, heap.remove(heap.size()-1));
        maxHeapify();
        return min;
    }

    public void insert(Element e){
        return;
    }

}
