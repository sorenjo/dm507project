import java.util.ArrayList;

public class DictBinTree implements Dict {
    BinNode root;

    /*
     * Constructs a new empty dictionary.
     */
    public DictBinTree() {
        BinNode root = null;
    }

    /*
     * Is key k in this dictionary
     */
    public boolean search( int k ){
        return search( root, k );
    }

    /*
     * Auxiliary method: search key k recursively in this dictionary.
     */
    private static boolean search( BinNode x, int k ){
        if ( x == null || k == x.key )
            return ( x != null );

        if ( k < x.key )
            return search( x.left, k );
        else
            return search( x.right, k );
    }

    /*
     * Insert key k in this dictionary.
     */
    public void insert( int k ){
        BinNode y = null;
        BinNode x = root;

        BinNode z = new BinNode( k );

        while ( x != null ) {
            y = x;
            if ( z.key < x.key )
                x = x.left;
            else 
                x = x.right;
        }
        if ( y == null)
            root = z;
        else if ( z.key < y.key )
            y.left = z;
        else 
            y.right = z;
    }

    /*
     * Traverse through this tree inorder.
     */
    public ArrayList< Integer > orderedTraversal() {
        ArrayList< Integer > list = new ArrayList< Integer > ();
        orderedTraversal( list, root );
        return list;
    }

    /*
     * Auxiliary method for inorder traversal.
     */
    private static void orderedTraversal( ArrayList< Integer > list, BinNode x ){
        if ( x != null){
            orderedTraversal( list, x.left );
            list.add( x.key );
            orderedTraversal( list, x.right );
        }
    }

    /*
     * Inner class for representing nodes in the binary tree.
     */
    private static class BinNode {
        public BinNode left;
        public BinNode right;
        public int key;

        /*
         * Constructs a new BinNode with key k and no children.
         */
        private BinNode( int k ){
            left = null;
            right = null;
            key = k;
        }
    }
}
