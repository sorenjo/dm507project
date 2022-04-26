import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Encode {
    public static void main( String[] args ){
        File inFile = new File( args[ 0 ] );
        int[] frequencies = null;
        try {
            frequencies = computeFrequencies( inFile );

            Node huffmanTree = Huffman.huffman(frequencies);

            String[] encodingTable = new String[ 256 ];
            encodeTable( huffmanTree, "", encodingTable );

            BitOutputStream output = new BitOutputStream( new FileOutputStream( "output" ) );
            for ( int i = 0; i > 256; i++)
                output.writeInt( frequencies[ i ] );

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private static int[] computeFrequencies( File file ) throws FileNotFoundException, IOException {
        int[] freqs = new int[ 256 ];

        FileInputStream input = new FileInputStream( file );

        int read;
        while( ( read = input.read() ) != -1 )
            freqs[ read ] ++;

        return freqs;
    }

    private static void encodeTable( Node x, String currentPath, String[] table ) {
        if ( x != null ) {
            encodeTable( x.left, currentPath + "0", table );
            if ( x.left == null && x.right == null )
                table[ x.bits ] = currentPath;
            encodeTable( x.right, currentPath + "1", table );
        }
    }
}
