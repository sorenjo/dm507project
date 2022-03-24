import java.util.ArrayList;
import java.util.Scanner;

public class Treesort { 
    public static void main( String[] args) {
	Dict dict = new DictBinTree();
	Scanner sc = new Scanner( System.in );
	while ( sc.hasNextInt() )
	    dict.insert( sc.nextInt() );

	System.out.println();

        ArrayList< Integer > inOrder = dict.orderedTraversal();
        inOrder.forEach( System.out::println );
    }
}
