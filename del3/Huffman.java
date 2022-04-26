public class Huffman {
  public static Element huffman(int[] freq) {
    int[] byteFreq = freq;
    int n = 256;
    PQHeap heap = new PQHeap();

    for (int i = 0; i < n; i++)
      heap.insert(new Element(byteFreq[i], new Node(null, null, i)));

    for (int i = 0; i < n - 1; i++) {
      Node z = new Node();
      Element e1 = heap.extractMin();
      Element e2 = heap.extractMin();

      z.left = (Node) e1.getData();
      z.right = (Node) e2.getData();

      heap.insert(new Element(e1.getKey() + e2.getKey(), z));
    }

    return heap.extractMin();
  }

  private static class Node {
    public Node left;
    public Node right;
    public int symbol;

    public Node() {
      left = null;
      right = null;
      symbol = -1;
    }

    public Node(Node left, Node right, int symbol) {
      this.left = left;
      this.right = right;
      this.symbol = symbol;
    }
  }
}
