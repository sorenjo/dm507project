public class Node {
  public Node left;
  public Node right;
  public int bits;

  /*
  * Default constructor used for internal nodes in huffman tree
  */
  public Node() {
    left = null;
    right = null;
    bits = 0;
  }

  /*
  * Constructor used for leafs in huffman
  */
  public Node(int bits) {
    this.left = null;
    this.right = null;
    this.bits = bits;
  }
}
