public class Solver {
private	MinPQ<searchNode> solver;
private	int moves;
private	searchNode initialNode;
private	class searchNode implements Comparable<searchNode> {
		int manhattan;
		Board board;
		public searchNode(Board bo) {
			board = bo;
			manhattan = bo.manhattan() + moves;
			
		}
		@Override
		public int compareTo(searchNode o) {
			if (manhattan - o.manhattan >0) return 1;
			if (manhattan - o.manhattan <0) return -1;
			else		return 0 ;
		}
	}
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
    	solver = new MinPQ<>(2);
    	if (!initial.isGoal())
    	{
    		initialNode = new searchNode(initial);
    	 		solver.insert(initialNode);
    	}
    	
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
    	MinPQ<searchNode> solvertwin = new MinPQ<>(2);
    	solvertwin.insert(new searchNode(initialNode.board.twin()));
    	while (!solver.min().board.isGoal() &&!solvertwin.min().board.isGoal()) 
    	{
    		moves++;
        		for (Board board:solver.delMin().board.neighbors())
        		{
        		solver.insert(new searchNode(board));
        		
        		}
        		//System.out.println(solver.delMin().board.toString()+" "+solver.min().manhattan);
        		for (Board board:solvertwin.delMin().board.neighbors())
        		{
        			solvertwin.insert(new searchNode(board));
        			//System.out.println(solvertwin.min().board.toString());
        		}
        		
        	//	moves++;
			
    	}
    	if (solver.min().board.isGoal()) {
    		return true;
			
		}
    	else {
			return false;
		}
    
		
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
    	if (!isSolvable()) return -1;
    		return moves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
    	Queue<Board> solutionQueue = new Queue<>();
    	if (!isSolvable()) return null;
    	solver = new MinPQ<>();
    	solver.insert(initialNode);
    	while (!solver.min().board.isGoal()) {
    		moves++;
    		for (Board board:solver.min().board.neighbors())
    		{
    		solver.insert(new searchNode(board));
    		}
    		
    		solutionQueue.enqueue(solver.delMin().board);
    		
    	}
    	solutionQueue.enqueue(solver.delMin().board);
    	return solutionQueue;
    }
    public static void main(String[] args) // solve a slider puzzle (given below)
    { // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
                 for (Board board : solver.solution())
                StdOut.println(board);
        
    	
    }
}
    }