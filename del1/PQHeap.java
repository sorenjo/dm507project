import java.util.ArrayList;

public class PQHeap implements PQ{
    private ArrayList<Element> heap;

    public PQHeap(){
        heap = new ArrayList<>();
    }

    public Element extractMin(){
        return heap.get(0);
    }

    public void insert(Element e){
        return;
    }

}
