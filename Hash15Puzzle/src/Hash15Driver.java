import java.util.*;

/**
 * Hash15Driver.java : generates board/runs solver
 * 
 * @author Bennett DuBois
 * 10/26/17
 *
 */

public class Hash15Driver {
	
	private static ArrayDeque<Hash15Board> solvePath = new ArrayDeque<Hash15Board>();
	private static final String SOLVED_BD = "123456789ABCDEF0";
	
	//returns array of possible moves on given location
	private static final int[][] transitions = {
			{1, 4}, {0, 2, 5}, {1, 6, 3}, {2, 7},
			{0, 5, 8}, {1, 4, 6, 9}, {2, 5, 7, 10}, {3, 6, 11},
			{4, 9, 12}, {5, 8, 10 ,13}, {6, 9, 11, 14}, {7, 10, 15},
			{8, 13}, {9, 12, 14}, {10, 13, 15}, {11, 14}};
	

	public static void main (String []args) {
		
		//creates Hash15Board and scrambles it with depth-many moves.
		final int depth = 10;
		Hash15Board newBd = new Hash15Board();
		newBd.scramble(depth);
		
		boolean solved = solver(newBd);
		
		if (solved) {
			System.out.println("Ta da!");
		}
		else {
			System.out.println("No solution found to search depth " + depth);
		}	
	}
	
	//breadth-first search solver
	private static boolean solver (Hash15Board bd) {
		
		solvePath.add(bd);
		
		while(!solvePath.isEmpty()) {
			
			Hash15Board front = solvePath.pop();
			
			if(front.getBd().equals(SOLVED_BD)) {
				
				Hash15Board parent = front.getParent();
				Stack<Hash15Board> solution = new Stack<Hash15Board>();
				
				while(parent != bd) {
					solution.push(parent);
					parent = parent.getParent();
				}
				
				while(!solution.empty()) {
					Hash15Board.printBoard(solution.pop().getBd());
				}
				
				Hash15Board.printBoard(front.getBd());
				
				return true;
			}
			
			//index of the empty square
			int empty = front.getBd().indexOf('0');
			
			//adds all possible moves available at the front-located Hash15Board
			for (int i = 0; i < transitions[empty].length; i++) {
				int[] moves = transitions[empty]; 
				int cell = moves[i];
				Hash15Board newBd = new Hash15Board(Hash15Board.move(front.getBd(), cell), front);
				solvePath.add(newBd);
			}
		}
		
		return false;
	}
}

