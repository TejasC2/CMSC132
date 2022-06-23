package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game,
 * specifically.
 * 
 * @author Dept of Computer Science, UMCP
 */

public class ClearCellGameModel extends GameModel {
	
	/* Include whatever instance variables you think are useful. */
	private Random random;
	private int score;
	/**
	 * Defines a board with empty cells.  It relies on the
	 * super class constructor to define the board.
	 * 
	 * @param rows number of rows in board
	 * @param cols number of columns in board
	 * @param random random number generator to be used during game when
	 * rows are randomly created
	 */
	public ClearCellGameModel(int rows, int cols, Random random) {
		//calls superclass constructor
		super(rows,cols);
		this.random = random;
	}

	/**
	 * The game is over when the last row (the one with index equal
	 * to board.length-1) contains at least one cell that is not empty.
	 */
	public boolean isGameOver() {
		boolean isOver = false;
		//checks if any cells in a row are not empty
		for(int i = 0; i < getBoard()[getBoard().length-1].length; i++) {
			if(getBoardCell(getBoard().length-1,i) != BoardCell.EMPTY) {
				isOver = true;
			}
		}
		return isOver;
		
	}

	/**
	 * Returns the player's score.  The player should be awarded one point
	 * for each cell that is cleared.
	 * 
	 * @return player's score
	 */
	public int getScore() {
		return this.score;
	}

	
	/**
	 * This method must do nothing in the case where the game is over.
	 * 
	 * As long as the game is not over yet, this method will do 
	 * the following:
	 * 
	 * 1. Shift the existing rows down by one position.
	 * 2. Insert a row of random BoardCell objects at the top
	 * of the board. The row will be filled from left to right with cells 
	 * obtained by calling BoardCell.getNonEmptyRandomBoardCell().  (The Random
	 * number generator passed to the constructor of this class should be
	 * passed as the argument to this method call.)
	 */
	public void nextAnimationStep() {
		if(this.isGameOver() == false) {
			for(int i = getRows()-1; i>=0; i--) {
				if(i != 0) {
					for(int k = 0; k < getCols(); k++) {
						setBoardCell(i,k,getBoardCell(i-1,k));
					}
				}else {
					for(int j = 0; j < getBoard()[i].length; j++) {
						setBoardCell(i,j,BoardCell.getNonEmptyRandomBoardCell(this.random));
					}
				}
			}

		}
	}

	/**
	 * This method is called when the user clicks a cell on the board.
	 * If the selected cell is not empty, it will be set to BoardCell.EMPTY, 
	 * along with any adjacent cells that are the same color as this one.  
	 * (This includes the cells above, below, to the left, to the right, and 
	 * all in all four diagonal directions.)
	 * 
	 * If any rows on the board become empty as a result of the removal of 
	 * cells then those rows will "collapse", meaning that all non-empty 
	 * rows beneath the collapsing row will shift upward. 
	 * 
	 * @throws IllegalArgumentException with message "Invalid row index" for 
	 * invalid row or "Invalid column index" for invalid column.  We check 
	 * for row validity first.
	 */
	public boolean isRowEmpty(int index) {
		boolean isRowEmpty = true;
		for(int i = 0; i < getBoard()[index].length; i++ ) {
			if(getBoardCell(index,i) != BoardCell.EMPTY) {
				isRowEmpty = false;
			}
		}
		return isRowEmpty;
	}
	public void processCell(int rowIndex, int colIndex) {
		if(rowIndex > getRows() || rowIndex < 0) {
			throw new IllegalArgumentException("Invalid row index");
		}
		if(colIndex > getCols() || colIndex < 0) {
			throw new IllegalArgumentException("Invalid column index");
		}

		BoardCell initial = getBoardCell(rowIndex,colIndex);
		if(getBoardCell(rowIndex,colIndex) != BoardCell.EMPTY) {
			if(getBoardCell(rowIndex,colIndex) == getBoardCell(rowIndex,colIndex)) {
				board[rowIndex][colIndex] = BoardCell.EMPTY;
				score++;
			}
			if(rowIndex-1 >= 0) {
				if(board[rowIndex-1][colIndex] == initial ) {
					board[rowIndex-1][colIndex] = BoardCell.EMPTY;
					score++;
				}
				if(colIndex-1 >= 0) {
					if(board[rowIndex-1][colIndex-1] == initial) {
						board[rowIndex-1][colIndex-1] = BoardCell.EMPTY;
						score++;
					}
				}
				if(colIndex+1 < getCols()) {
					if(board[rowIndex-1][colIndex+1] == initial) {
						board[rowIndex-1][colIndex+1] = BoardCell.EMPTY;
						score++;
					}
				}
			}
			if(rowIndex+1 < getRows()) {
				if(board[rowIndex+1][colIndex] == initial) {
					board[rowIndex+1][colIndex] = BoardCell.EMPTY;
					score++;
				}
				if(colIndex-1 >= 0) {
					if(board[rowIndex+1][colIndex-1] == initial) {
						board[rowIndex+1][colIndex-1] = BoardCell.EMPTY;
						score++;
					}
				}
				if(colIndex+1 < getCols()) {
					if(board[rowIndex+1][colIndex+1] == initial) {
						board[rowIndex+1][colIndex+1] = BoardCell.EMPTY;
						score++;
					}
				}
			}
			if(colIndex-1 >= 0) {
				if(board[rowIndex][colIndex-1] == initial) {
					board[rowIndex][colIndex-1] = BoardCell.EMPTY;
					score++;
				}
			}
			if(colIndex+1 < getCols()) {
				if(board[rowIndex][colIndex+1] == initial) {
					board[rowIndex][colIndex+1] = BoardCell.EMPTY;
					score++;
				}
			}		
		}	
		for(int rows = getRows()-2; rows > 0; rows--) {
			if(isRowEmpty(rows) == true) {
				for(int rows1 = rows; rows1 < getRows()-1; rows1++) {
					for(int cols = 0; cols < getCols(); cols++) {
						board[rows1][cols] = board[rows1+1][cols];
						board[rows1+1][cols] = BoardCell.EMPTY;
					}
				}
			}
		}
		
		
		
	}

}