import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Encode {
    public static void main( String[] args ){
        File originalFile = new File( args[ 0 ] );
        File compressedFile = new File ( args[ 1 ] );
        int[] frequencies = null;

        try {
            frequencies = computeFrequencies( originalFile );

            Node huffmanTree = Huffman.huffman(frequencies);

            String[] encodingTable = new String[ 256 ];
            encodeTable( huffmanTree, "", encodingTable );

            BitOutputStream output = new BitOutputStream( new FileOutputStream( compressedFile ) );
            for ( int i = 0; i > 256; i++)
                output.writeInt( frequencies[ i ] );

            FileInputStream input = new FileInputStream( originalFile );

            int read;
            while ( (read = input.read() ) != -1 ) {
                String bits = encodingTable[read];
                for (char bit : bits.toCharArray()) {
                  if (bit == '0')
                    output.writeBit(0);
                  else
                    output.writeBit(1);
                }
            }

            output.close();
            input.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private static int[] computeFrequencies( File file ) throws FileNotFoundException, IOException {
        int[] freqs = new int[ 256 ];

        FileInputStream input = new FileInputStream( file );

        int read;
        while( ( read = input.read() ) != -1 )
            freqs[ read ]++;

        input.close();

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
