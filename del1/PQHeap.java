import java.util.ArrayList;

public class PQHeap implements PQ{
    private ArrayList<Element> heap;

    public PQHeap() {
      heap = new ArrayList<>();
    }

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
        Element temp = heap.get(i);
        heap.set(i, heap.get(smallest));
        heap.set(smallest, temp);
        heapify(smallest);
      }
    }

    public Element extractMin(){
      Element min = heap.get(0);
      heap.set(0, heap.get(heap.size()-1));
      heap.remove(heap.size()-1);
      heapify(0);
      return min;
    }

    public void insert(Element e){
      heap.add(e);
      int i = heap.size()-1;
      int parent;
      while (i > 0 && heap.get((parent = (i-1)/2)).getKey() > heap.get(i).getKey()) {
        Element temp = heap.get(i);
        heap.set(i, heap.get(parent));
        heap.set(parent, temp);
        i = parent;
      }
    }
}
