import java.util.ArrayList;

public class DictBinTree implements Dict{
    BinNode root;

    public DictBinTree(){
        BinNode root = null;
    }

    public boolean search( int k ){
        return search( root, k );
    }

    private static boolean search( BinNode x, int k ){
        if ( x == null || k == x.key )
            return ( x != null );

        if ( k < x.key )
            return search(x.left, k);
        else
            return search(x.right, k);
    }

    public void insert(int k){
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

    public ArrayList< Integer > orderedTraversal(){
        ArrayList< Integer > list = new ArrayList< Integer > ();
        orderedTraversal( list, root );
        return list;
    }

    private static void orderedTraversal( ArrayList< Integer > list, BinNode x ){
        if ( x != null){
            orderedTraversal(list, x.left);
            list.add(x.key);
            orderedTraversal(list, x.right);
        }
    }

    private static class BinNode{
        public BinNode left;
        public BinNode right;
        public int key;

        private BinNode(int key){
            left = null;
            right = null;
            this.key = key;
        }
    }
}
