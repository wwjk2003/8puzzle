

public class Board {
	private	int[][] board;
	private int preStatue = 0;
    public Board(int[][] blocks)           // construct a board from an N-by-N array of blocks
                                           // (where blocks[i][j] = block in row i, column j)
    {
    	board = blocks;
    }
   
    public int dimension()                 // board dimension N
    {
    	return board.length;
    }
    public int hamming()                   // number of blocks out of place
    {
    	int hamming = 0;
    	for (int i = 0; i < dimension(); i++)
    	{	for (int j = 0; j < dimension(); j++)
    	{
    		if (board[i][j] != i * dimension() + j + 1 &&board[i][j] != 0)
    			hamming++;
    	}
    	
    	}
    	
    	return hamming;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
    	int i1,j1,manhattan = 0;
    	for (int i = 0; i < dimension(); i++)
    	{	for (int j = 0; j < dimension(); j++)
    	{
    		if (board[i][j] != 0){
    		i1 = (board[i][j]-1) / dimension();
    		j1 = (board[i][j]-1) % dimension();
    		int incre = i1 - i +j1 - j;
    		
    		if (incre <0) incre*= -1;
    	 		manhattan += incre  ;
    	}
    		}
    	}
    	return manhattan;
    }
    public boolean isGoal()                // is this board the goal board?
    {
    	if (hamming() == 0)
    		return true;
    	else return false;
    }
    public Board twin()                    // a board that is obtained by exchanging two adjacent blocks in the same row
    {
    	int i = 0, j = 0;
    	while (board[i][j] ==0 || board[i][j+1] == 0) {
		if (j == dimension() - 2)
		{
			i++;
			j = 0;
		}
		else j ++;
			
		}
    	int[][] copy = new int[dimension()][dimension()];
		for (int k = 0; k < dimension(); k++) {
			for (int k2 = 0; k2 < dimension(); k2++) {
				copy[k][k2] = board[k][k2];
			}
		}
    	int temp = copy[i][j];
    	copy[i][j] = copy[i][j+1];
    	copy[i][j+1] = temp;
    	return new Board(copy);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
    	 if (y == this) return true;
         if (y == null) return false;
         if (y.getClass() != this.getClass()) return false;
         Board that = (Board) y;
         return (this.board == that.board);
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
    	Queue<Board> neighborlist = new Queue<Board>();
    		int i=0,j=0;
    			while (board[i][j] != 0)
    			{	if (j == dimension() - 1)
    				{
    					i++;
    					j = 0;
    				}
    				else j++;
    			}
    		
    						if ( i + 1 < dimension() && preStatue != 4)
    			{//System.out.println(toString());
    				Board neighbor = new Board(copy());
    				
    				neighbor.swap(i, j, i+1, j);
    				neighbor.preStatue = 2;
    				neighborlist.enqueue(neighbor);
    				
    			}
    			if (i - 1 >= 0  && preStatue != 2)
    			{//System.out.println(toString());
    				Board neighbor = new Board(copy());
    			
    				neighbor.swap(i, j, i-1, j);
    				neighbor.preStatue = 4;
    				neighborlist.enqueue(neighbor);
    			
    			}
    			if ( j + 1 < dimension() && preStatue != 3 )
    			{//System.out.println(toString());
    				Board neighbor = new Board(copy());
    		
    				neighbor.swap(i, j, i, j+1);
    				neighbor.preStatue = 1;
    				neighborlist.enqueue(neighbor);
    				
    			}
    			if (j - 1 >= 0  && preStatue !=1)
    			{//System.out.println(toString());
    				Board neighbor = new Board(copy());
    			
    				neighbor.swap(i, j, i, j - 1);
    				neighbor.preStatue = 3;
    				neighborlist.enqueue(neighbor);
    		
    			}
    			return neighborlist;
    }
    private void swap(int i1, int j1, int i2, int j2) {
    	int temp = board[i1][j1];
    	board[i1][j1] = board [i2][j2];
    	board[i2][j2] = temp;
		
	}
    private int[][] copy(){
    	int[][] copy =  new int[dimension()][dimension()];
    	for (int i = 0; i < dimension(); i++) {
    		for (int j = 0; j < dimension(); j++) {
			copy[i][j] = board[i][j];	
			}
			
		}
    	return copy;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
    	StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", board[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
   

    public static void main(String[] args) // unit tests (not graded)
    {
    	  In in = new In(args[0]);
          int N = in.readInt();
          int[][] blocks = new int[N][N];
          for (int i = 0; i < N; i++)
              for (int j = 0; j < N; j++)
                  blocks[i][j] = in.readInt();
          Board initial = new Board(blocks);
       //  System.out.println( initial.toString());
      //  System.out.println(initial.dimension()+" "+initial.hamming()+" "+initial.manhattan());
      //   System.out.println(initial.twin().toString());
         for (Board board : initial.neighbors())
         {	 System.out.println(board.toString()+" "+ board.isGoal()+" "+board.manhattan());
//         for (Board board2:board.neighbors())
//         {
//        	 System.out.println(board2.toString()+" "+board2.isGoal());
//         }
         }
    }
}