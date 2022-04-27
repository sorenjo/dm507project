import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Decode {
  public static void main(String[] args) {
    File compressedFile = new File( args[0] );
    File decodedFile = new File( args[1] );
    int[] frequencies = new int[ 256 ];

    int charCount = 0;


    try{
      BitInputStream input = new BitInputStream( new FileInputStream( compressedFile ) );
      FileOutputStream output = new FileOutputStream( decodedFile ); 
      for ( int i = 0; i < 256; i++ ){
        int n = input.readInt();
        frequencies[ i ] = n;
        charCount += n;
      }
      Node huffmanTree = Huffman.huffman( frequencies );
      Node tempTree = huffmanTree;
      while( charCount > 0 ){
        // read new character
        while( tempTree.left != null ){ //&& tempTree.right != null
          int bit = input.readBit();
          tempTree= bit == 0 ? tempTree.left : tempTree.right;
        } 
        output.write( tempTree.bits );
        charCount--;
        tempTree = huffmanTree;
      }


    } catch ( Exception e ) {
      e.printStackTrace();
    }
  }
}
