public class Huffman {
  public static Node huffman(int[] freq) {
    int[] frequencies = freq;
    int n = 256;
    PQHeap heap = new PQHeap();

    for (int i = 0; i < n; i++)
      heap.insert(new Element(frequencies[i], new Node(null, null, i)));

    for (int i = 0; i < n - 1; i++) {
      Node z = new Node();
      Element e1 = heap.extractMin();
      Element e2 = heap.extractMin();

      z.left = (Node) e1.getData();
      z.right = (Node) e2.getData();

      heap.insert(new Element(e1.getKey() + e2.getKey(), z));
    }

    return (Node) heap.extractMin().getData();
  }
}
