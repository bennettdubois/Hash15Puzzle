/**
 * Hash15Board.java : generates 15 puzzle board and uses breadth-first search 
 * to traverse a tree of possible boards until a solution is found, 
 * 
 * @author Bennett DuBois
 * 10/25/17
 *
 */

import java.util.Random;

public class Hash15Board {
	
	private static final String SOLVED_BD = "123456789ABCDEF0";
	
	//returns array of possible moves on a given location
	private static final int[][] transitions = {
			{1, 4}, {0, 2, 5}, {1, 6, 3}, {2, 7},
			{0, 5, 8}, {1, 4, 6, 9}, {2, 5, 7, 10}, {3, 6, 11},
			{4, 9, 12}, {5, 8, 10 ,13}, {6, 9, 11, 14}, {7, 10, 15},
			{8, 13}, {9, 12, 14}, {10, 13, 15}, {11, 14}};
	
	private Random rand = new Random();
	private Hash15Board parent;
	private String bd;
	
	public Hash15Board() {
		parent = null;
		
	}
	
	public Hash15Board(String bd, Hash15Board parent) {
		this.bd = bd;
		this.parent = parent;
	}
	
	
	// randomly scrambles board with depth-many moves
	public void scramble(int depth) {  
	int empty = 15; //location of 0 from 0-15
	bd = SOLVED_BD; 
		for (int i = 0; i < depth; i++) { 
			int[] moves = transitions[empty]; 
			int cell = moves[rand.nextInt(moves.length)];  
			bd = move(bd, cell); 
			empty = cell; 
		}
		printBoard(bd);
	}
	
	//move the empty cell to the desired location
	public static String move(String bd, int cell) {
		String newBd; 
		char fromChar = bd.charAt(cell); 
		newBd = bd.replace('0', 'X').replace(fromChar, '0').replace('X', fromChar);  
		return newBd; 
	}
	
	//print the board in a 4x4 grid using println
	public static void printBoard(String bd) {  
		for (int r = 0; r < 4; r++) { 
			for (int c = 0; c < 4; c++) {  
				System.out.print(bd.charAt(r*4 + c) + " "); 
			} 
			System.out.println();  
		} 
		System.out.println();   
	}

	public Hash15Board getParent() {
		return parent;
	}
	
	public String getBd() {
		return bd;
	}
	
}
