public class Node {
  public Node left;
  public Node right;
  public int bits;

  public Node() {
    left = null;
    right = null;
    bits = 0;
  }

  public Node(Node left, Node right, int bits) {
    this.left = left;
    this.right = right;
    this.bits = bits;
  }
}
