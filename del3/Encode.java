import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Encode {

    /*
    * The main method encodes a file from args[0] and saves a compressed file
    * called args[1].
    */
    public static void main( String[] args ){
        File originalFile = new File( args[ 0 ] );
        File compressedFile = new File ( args[ 1 ] );
        int[] frequencies = null;

        try {
            frequencies = computeFrequencies( originalFile );

            // Pointer to root of huffman tree of the byte frequencies.
            Node huffmanTree = Huffman.huffman(frequencies);

            // Make an array of prefix codes for every byte using the huffman tree.
            String[] encodingTable = new String[ 256 ];
            encodeTable( huffmanTree, "", encodingTable );

            // Save the frequencies of every byte as the first 1024 bytes of the compressed file.
            BitOutputStream output = new BitOutputStream( new FileOutputStream( compressedFile ) );
            for ( int i = 0; i < 256; i++)
                output.writeInt( frequencies[ i ] );

            FileInputStream input = new FileInputStream( originalFile );
            int read;

            // Read every byte of input file, look up the prefix code of the bytes,
            // and write the bit string to the compressed file.
            while ( (read = input.read() ) != -1 ) {
                String bits = encodingTable[read];
                for (char bit : bits.toCharArray()) {
                    int b = bit == '0' ? 0 : 1;
                    output.writeBit(b);
                }
            }

            // Remember to close the output stream to write the buffered bits
            output.close();
            input.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /*
    * Computes the frequencies of every byte in file and returns them in an array
    */
    private static int[] computeFrequencies( File file ) throws FileNotFoundException, IOException {
        int[] freqs = new int[ 256 ];

        FileInputStream input = new FileInputStream( file );

        int read;
        while( ( read = input.read() ) != -1 )
            freqs[ read ]++;

        input.close();

        return freqs;
    }

    /*
    * Recursively find the prefix code of every leaf in a huffman tree,
    * and save them in array String[] table, where the index is the numerical
    * representation of the byte stored in the leaf.
    */
    private static void encodeTable( Node x, String currentPath, String[] table ) {
        if ( x != null ) {
            encodeTable( x.left, currentPath + "0", table );
            if ( x.left == null && x.right == null )
                table[ x.bits ] = currentPath;
            encodeTable( x.right, currentPath + "1", table );
        }
    }
}
