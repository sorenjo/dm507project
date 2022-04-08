/*
 * Sebastian Brendel sebre21@student.sdu.dk
 * Søren Johansen sojoh21@student.sdu.dk
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Treesort { 
    public static void main( String[] args) {
	Dict dict = new DictBinTree(); //Dictionary for storing input.
	Scanner sc = new Scanner( System.in );
	while ( sc.hasNextInt() )
	    dict.insert( sc.nextInt() ); //Read from stdin and insert to the ordered dictionary.

	System.out.println();

        ArrayList< Integer > inOrder = dict.orderedTraversal();
        inOrder.forEach( System.out::println ); //Write to stdout all numbers inorder.
    }
}
