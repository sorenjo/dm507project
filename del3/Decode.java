import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Decode {

  /*
  * The main method decodes a compress file from args[0] and saves a decoded file
  * named args[1].
  */
  public static void main(String[] args) {
    File compressedFile = new File( args[0] );
    File decodedFile = new File( args[1] );
    int[] frequencies = new int[ 256 ];

    // Counter so we know when to stop reading new bits.
    int byteCount = 0;

    try {
      BitInputStream input = new BitInputStream( new FileInputStream( compressedFile ) );
      FileOutputStream output = new FileOutputStream( decodedFile );

      // Read frequencies of every byte in original file.
      for ( int i = 0; i < 256; i++ ){
        int n = input.readInt();
        frequencies[ i ] = n;
        byteCount += n;
      }

      // Make an array of prefix codes for every byte using the huffman tree.
      Node huffmanTree = Huffman.huffman( frequencies );

      // Temp node used to walk through the tree.
      Node tempTree = huffmanTree;

      while( byteCount > 0 ){

        // Walk through tree by reading bits of compressed file.
        while( tempTree.left != null ) { // && tempTree.right != null (not necessary)
          int bit = input.readBit();
          tempTree = bit == 0 ? tempTree.left : tempTree.right;
        }

        // Write byte found in leaf of huffman tree.
        output.write( tempTree.bits );
        byteCount--;
        tempTree = huffmanTree;
      }


    } catch ( Exception e ) {
      e.printStackTrace();
    }
  }
}
