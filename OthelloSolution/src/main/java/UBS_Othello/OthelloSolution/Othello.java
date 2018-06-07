package UBS_Othello.OthelloSolution;

import java.util.Arrays;
/**
 * 
 * @author Amit Wakade
 *
 */
public class Othello {
	
	private final int MAX = 8;
	private final int MAX_MOVES = 64;
	private final char X = 'X';
	private final char O = 'O';
	private final char emptyChar = '-';
	
	private char[][]  board = new char[MAX][MAX];
	private enum direction { UP, DOWN, RIGHT, LEFT, RIGHTDIAGUP, RIGHTDIAGDOWN, LEFTDIAGUP,LEFTDIAGDOWN}
	private int countX = 0; 
	private int countO = 0;
	private int currentX, currentY = 0;
	private boolean isPlacementValid = true;
	
	public static boolean PlayON = true;
	/**
	 * This Method actually is the move and place the player's Reversi disc
	 * 		
	 * @param input Co-ordinates in 8*8 Othello board Row and Column Index 
	 * @param currentPlayer Current Player 'X' or 'O'
	 */
	public void play(String input, App.players currentPlayer)
	{	
		currentX = Character.getNumericValue(input.charAt(0));
		currentY = Character.getNumericValue(input.charAt(1));
		if(validateInput(currentX, currentY, currentPlayer))
		{
			checkAndConvertReversi(currentX, currentY, currentPlayer);
		}		
	}
	
	/**
	 * Othello Class Constructor
	 */
	public Othello()
	{
		// Fill each row with '-'
		for (char[] row: board)
		    Arrays.fill(row, emptyChar);
		
		board [3][3] = O;
		board [3][4] = X;
		board [4][3] = X;
		board [4][4] = O;
		
		countX += 2;
		countO += 2;
		
		isPlacementValid = true;
	}
	
	/**
	 * Prints the Board with Current State of the Board with Play.
	 */
	public void printBoard()
	{		
		for(int i =0; i <MAX; i++)
		{
			System.out.print((i+1) +" ");
			for (int j =0; j<MAX; j++)
				System.out.print(board[i][j]);
			
			System.out.println();	
		}
		System.out.println("  abcdefgh\n");
	}

	/**
	 * This method validates input based on rowNumber, columnNumber and Current Player provided.
	 * 
	 * @param rNum Row Number of 8*8 Board
	 * @param cNum Column Number of 8*8 Board
	 * @param currentPlayer Current Player 'X' or 'O
	 * @return true if the input is valid and the move is valid, false, otherwise.
	 */
	public boolean validateInput(int rNum, int cNum, App.players currentPlayer)
	{
		boolean isValid = false;
		
		if (rNum > 7 || rNum < 0 || cNum > 7 || cNum < 0)
			isValid = false;
		else
			isValid = true;
				
		if (isValid)
			isValid = isValidMove(rNum, cNum, currentPlayer);
		
		return isValid;
	}
	
	/**
	 * Checks whether the move is within the Board and next to existing 
	 * discs 
	 * @param rNum Row Number of 8*8 Board
	 * @param cNum Column Number of 8*8 Board
	 * @param currentPlayer Current Player 'X' or 'O
	 * @return true if the move is valid, false, otherwise.
	 */
	private boolean isValidMove(int rNum, int cNum, App.players currentPlayer)
	{
		boolean isValidMove = false;
		
		// Check if the Move is not already played move
		if (board[rNum][cNum] != emptyChar)
		{
			return isValidMove;
		}
		
		if (currentPlayer == App.players.X)
		{	
			if ((rNum-1 >= 0 && board[rNum-1][cNum] == O && isValidPlacement(rNum-1,cNum, X, Othello.direction.UP))
				|| (cNum-1 >= 0 && board[rNum][cNum-1] == O && isValidPlacement(rNum,cNum-1, X, Othello.direction.LEFT))
				|| (rNum+1 < MAX && board[rNum+1][cNum] == O && isValidPlacement(rNum+1,cNum, X, Othello.direction.DOWN))
				|| (cNum+1 < MAX && board[rNum][cNum+1] == O && isValidPlacement(rNum,cNum+1, X, Othello.direction.RIGHT))
 				|| ((rNum+1 < MAX && cNum+1 < MAX) && board[rNum+1][cNum+1] == O && isValidPlacement(rNum+1,cNum+1, X, Othello.direction.RIGHTDIAGDOWN))
 				|| ((rNum-1 >= 0 && cNum-1 >= 0) && board[rNum-1][cNum-1] == O && isValidPlacement(rNum-1,cNum-1, X, Othello.direction.LEFTDIAGUP))
 				|| ((rNum+1 < MAX && cNum-1 >= 0) && board[rNum+1][cNum-1] == O && isValidPlacement(rNum+1,cNum-1, X, Othello.direction.LEFTDIAGDOWN))
 				|| ((rNum-1 >= 0 && cNum+1 < MAX) && board[rNum-1][cNum+1] == O && isValidPlacement(rNum-1,cNum+1, X, Othello.direction.RIGHTDIAGUP))
 				)
				{
					isValidMove = true;
				}		
		}
		else
		{
			if ((rNum-1 >= 0 && board[rNum-1][cNum] == X && isValidPlacement(rNum-1,cNum, O, Othello.direction.UP))
					|| (cNum-1 >= 0 && board[rNum][cNum-1] == X && isValidPlacement(rNum,cNum-1, O, Othello.direction.LEFT))
					|| (rNum+1 < MAX && board[rNum+1][cNum] == X && isValidPlacement(rNum+1,cNum, O, Othello.direction.DOWN))
					|| (cNum+1 < MAX && board[rNum][cNum+1] == X && isValidPlacement(rNum,cNum+1, O, Othello.direction.RIGHT))
	 				|| ((rNum+1 < MAX && cNum+1 < MAX) && board[rNum+1][cNum+1] == X && isValidPlacement(rNum+1,cNum+1, O, Othello.direction.RIGHTDIAGDOWN))
	 				|| ((rNum-1 >= 0 && cNum-1 >= 0) && board[rNum-1][cNum-1] == X && isValidPlacement(rNum-1,cNum-1, O, Othello.direction.LEFTDIAGUP))
	 				|| ((rNum+1 < MAX && cNum-1 >= 0) && board[rNum+1][cNum-1] == X && isValidPlacement(rNum+1,cNum-1, O, Othello.direction.LEFTDIAGDOWN))
	 				|| ((rNum-1 >= 0 && cNum+1 < MAX) && board[rNum-1][cNum+1] == X && isValidPlacement(rNum-1,cNum+1, O, Othello.direction.RIGHTDIAGUP))
	 				)
				{
					isValidMove = true;
				}
		}

		 return isValidMove;
	}
	
	/**
	 * This method checks whether the Move results in capturing at least one of the opposite player 
	 * discs in straight line either horizontally, vertically or diagonally so that the current player disc
	 * and old same player disc
	 * 
	 * @param rNum Row Number of 8*8 Board of the opposite disc next to currentPlayer disc
	 * @param cNum Column Number of 8*8 Board of the opposite disc next to currentPlayer disc
	 * @param currentPlayer Current Player 'X' or 'O
	 * @param dir direction on the board Left, Right, Up, Down, Diagonally up or Down.
	 * @return
	 */
	private boolean isValidPlacement(int rNum, 
			int cNum, 
			char curPlayingChar, 
			Othello.direction dir)
	{
		boolean isValidPlacement = false;
		//boolean curPlayingCharIsFound = false;
		int i=1,j=1;
		switch(dir)
		{		    
			case UP:
				i=1;				
				while (rNum-i >= 0 
					&& board[rNum-i][cNum] != curPlayingChar
					&& board[rNum-i][cNum] != emptyChar)
				{
					i++;					
				}
				if (rNum-i >= 0 && board[rNum-i][cNum] == curPlayingChar)				
					isValidPlacement = true;				
				break;
			case DOWN:
				i=1;				
				while (rNum+i < MAX 
					&& board[rNum+i][cNum] != curPlayingChar
					&& board[rNum+i][cNum] != emptyChar)
				{
					i++;					
				}
				if (rNum+i < MAX && board[rNum+i][cNum] == curPlayingChar)				
					isValidPlacement = true;				
				break;
			case RIGHT:
				i=1;				
				while (cNum+i < MAX 
					&& board[rNum][cNum+i] != curPlayingChar
					&& board[rNum][cNum+i] != emptyChar)
				{
					i++;					
				}
				if (cNum+i < MAX && board[rNum][cNum+i] == curPlayingChar)				
					isValidPlacement = true;				
				break;
			case LEFT:
				i=1;				
				while (cNum-i >= 0 
					&& board[rNum][cNum-i] != curPlayingChar
					&& board[rNum][cNum-i] != emptyChar)
				{
					i++;					
				}
				if (cNum-i >= 0 && board[rNum][cNum-i] == curPlayingChar)				
					isValidPlacement = true;				
				break;
			case RIGHTDIAGUP:
				
				i = 1; j = 1; 
				while(rNum-i >= 0 && cNum+j < MAX 
					  && board[rNum-i][cNum+j] != curPlayingChar
					  && board[rNum-i][cNum+j] != emptyChar)
				{
					i++;
					j++;
				}
				if (rNum-i >= 0 && cNum+j < MAX
					&& board[rNum-i][cNum+j] == curPlayingChar)
						isValidPlacement = true;
				break;
			case LEFTDIAGUP:
				
				i=1; j=1;
				while(rNum-i >= 0 && cNum-j >=0 
						&& board[rNum-i][cNum-j] != curPlayingChar
						&& board[rNum-i][cNum-j] != emptyChar)
				{
					i++;
					j++;
				}
				if (rNum-i >= 0 && cNum-j >=0
					&& board[rNum-i][cNum-j] == curPlayingChar)
						isValidPlacement = true;
				break;
			case RIGHTDIAGDOWN:
				
				i = 1; j=1; 
				while(rNum+i < MAX && cNum+j < MAX 
					  && board[rNum+i][cNum+j] != curPlayingChar
					  && board[rNum+i][cNum+j] != emptyChar)
				{
					i++;
					j++;
				}			
				if (rNum+i < MAX && cNum+j < MAX
					&& board[rNum+i][cNum+j] == curPlayingChar)
						isValidPlacement = true;				
				break;
			case LEFTDIAGDOWN:
				
				i=1; j=1; 
				while(rNum+i < MAX && cNum-j >= 0 
						&& board[rNum+i][cNum-j] != curPlayingChar
						&& board[rNum+i][cNum-j] != emptyChar)
				{
					i++;
					j++;
				}
				if (rNum+i < MAX && cNum-j >= 0
					&& board[rNum+i][cNum-j] == curPlayingChar)
						isValidPlacement = true;
				break;
			default: break;			
		}
				
		isPlacementValid = isValidPlacement;
		return isValidPlacement;
	}
	
	/**
	 * This is main heart of the program where we check the Opposite Player's discs captured 
	 * between current position of the currentPlayer Discs and an old disc of the current player 
	 * on the board.
	 * 
	 * @param rNum Row Number of 8*8 Board
	 * @param cNum Column Number of 8*8 Board
	 * @param currentPlayer Current Player 'X' or 'O
	 */
	private void checkAndConvertReversi(int rNum, int cNum, App.players currentPlayer)
	{
		if (currentPlayer == App.players.X)
		{
			board[rNum][cNum] = X;
			countX++;
			if(cNum+1 < MAX && board[rNum][cNum+1] == O)
				reversi(rNum, cNum+1, X, direction.RIGHT);
			if(cNum-1 >= 0 && board[rNum][cNum-1] == O)
				reversi(rNum, cNum-1, X, direction.LEFT);
			if(rNum+1 < MAX && board[rNum+1][cNum] == O)
				reversi(rNum+1, cNum, X, direction.DOWN);
			if(rNum-1 >= 0 && board[rNum-1][cNum] == O)
				reversi(rNum-1, cNum, X, direction.UP);
			if(rNum+1 < MAX && cNum+1 < MAX && board[rNum+1][cNum+1] == O)
				reversi(rNum+1, cNum+1, X, direction.RIGHTDIAGDOWN);
			if(rNum-1 >= 0 && cNum-1 >= 0 && board[rNum-1][cNum-1] == O)
				reversi(rNum-1, cNum-1, X, direction.LEFTDIAGUP);
			if(rNum-1 >= 0 && cNum+1 < MAX && board[rNum-1][cNum+1] == O)
				reversi(rNum-1, cNum+1, X, direction.RIGHTDIAGUP);
			if(rNum+1 < MAX && cNum-1 >= 0 && board[rNum+1][cNum-1] == O)
				reversi(rNum+1, cNum-1, X, direction.LEFTDIAGDOWN);
		}
		else
		{
			board[rNum][cNum] = O;
			countO++;
			if(cNum+1 < MAX && board[rNum][cNum+1] == X)
				reversi(rNum, cNum+1, O, direction.RIGHT);
			if(cNum-1 >= 0 && board[rNum][cNum-1] == X)
				reversi(rNum, cNum-1, O, direction.LEFT);
			if(rNum+1 < MAX && board[rNum+1][cNum] == X)
				reversi(rNum+1, cNum, O, direction.DOWN);
			if(rNum-1 >= 0 && board[rNum-1][cNum] == X)
				reversi(rNum-1, cNum, O, direction.UP);
			if(rNum+1 < MAX && cNum+1 < MAX && board[rNum+1][cNum+1] == X)
				reversi(rNum+1, cNum+1, O, direction.RIGHTDIAGDOWN);
			if(rNum-1 >= 0 && cNum-1 >= 0 && board[rNum-1][cNum-1] == X)
				reversi(rNum-1, cNum-1, O, direction.LEFTDIAGUP);
			if(rNum-1 >= 0 && cNum+1 < MAX && board[rNum-1][cNum+1] == X)
				reversi(rNum-1, cNum+1, O, direction.RIGHTDIAGUP);
			if(rNum+1 < MAX && cNum-1 >= 0 && board[rNum+1][cNum-1] == X)
				reversi(rNum+1, cNum-1, O, direction.LEFTDIAGDOWN);
		}
	}
	
	/**
	 * This method actually converts the captured opposite side discs to current player's discs.
	 * main function of the program.
	 * 
	 * @param rNum Row Number of 8*8 Board of the opposite disc next to currentPlayer disc
	 * @param cNum Column Number of 8*8 Board of the opposite disc next to currentPlayer disc
	 * @param reversingChar Current Player Character 'X' and 'O'
	 * @param dir direction on the board Left, Right, Up, Down, Diagonally up or Down.
	 */
	private void reversi(int rNum, int cNum, char reversingChar, Othello.direction dir)
	{
		int count=0;
		
		if (rNum >= 0 && rNum < MAX && cNum >=0 && cNum < MAX)
		{
			switch (dir)
			{
				case RIGHT:								

					int j = 0, findOldReversingCharLoc=-1;
					while(cNum+j < MAX 
						  && board[rNum][cNum+j] != reversingChar
						  && board[rNum][cNum+j] != emptyChar)
					{
						j++;					
					}
					if (cNum+j < MAX
						&& board[rNum][cNum+j] == reversingChar)
							findOldReversingCharLoc = cNum + j;
					j=0;
					while(cNum+j < MAX 
						  && cNum+j < findOldReversingCharLoc
						  && findOldReversingCharLoc != -1)
					{
						board[rNum][cNum+j] = reversingChar;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;				
			
				case LEFT:
				
					j = 0; findOldReversingCharLoc=-1;
					while(cNum-j >= 0 
						  && board[rNum][cNum-j] != reversingChar
						  && board[rNum][cNum-j] != emptyChar)
					{
						j++;										
					}
					if (cNum-j >= 0
						&& board[rNum][cNum-j] == reversingChar)
							findOldReversingCharLoc = cNum - j;
					j=0;
					while(cNum-j >= 0 
						  && cNum-j > findOldReversingCharLoc
						  && findOldReversingCharLoc != -1)
					{
						board[rNum][cNum-j] = reversingChar;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;
			
				case DOWN:
			
					j = 0; findOldReversingCharLoc = -1;
					while(rNum+j < MAX 
						  && board[rNum+j][cNum] != reversingChar
						  && board[rNum+j][cNum] != emptyChar)
					{
						j++;										
					}
					if (rNum+j < MAX
						&& board[rNum+j][cNum] == reversingChar)
							findOldReversingCharLoc = rNum + j;
					j = 0;
					while(rNum+j < MAX 
						  && rNum + j < findOldReversingCharLoc
						  && findOldReversingCharLoc != -1)
					{
						board[rNum+j][cNum] = reversingChar;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;
			
				case UP:
					
					j = 0; findOldReversingCharLoc = -1;
					while(rNum-j >= 0 
						  && board[rNum-j][cNum] != reversingChar
						  && board[rNum-j][cNum] != emptyChar)
					{
						j++;					
					}
					if (rNum-j >= 0
						&& board[rNum-j][cNum] == reversingChar)
							findOldReversingCharLoc = rNum - j;
					j = 0;
					while(rNum-j >= 0 
						  && rNum-j > findOldReversingCharLoc
						  && findOldReversingCharLoc != -1)
					{
						board[rNum-j][cNum] = reversingChar;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;
								
				case RIGHTDIAGDOWN:
					
					j = 0;
					int i = 0, findOldReversingCharLocR=-1, findOldReversingCharLocC=-1;
					while(rNum+i < MAX && cNum+j < MAX 
						  && board[rNum+i][cNum+j] != reversingChar
						  && board[rNum+i][cNum+j] != emptyChar)
					{
						i++;
						j++;
					}			
					if (rNum+i < MAX && cNum+j < MAX
						&& board[rNum+i][cNum+j] == reversingChar)
					{
						findOldReversingCharLocR = rNum + i;
						findOldReversingCharLocC = cNum + j;
					}
					i=j=0;
					while(rNum+i < MAX && cNum+j < MAX
						  && rNum+i < findOldReversingCharLocR
						  && cNum+j < findOldReversingCharLocC
						  && findOldReversingCharLocR != -1
						  && findOldReversingCharLocC != -1)
					{
						board[rNum+i][cNum+j] = reversingChar;
						i++;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;
				
				case LEFTDIAGDOWN:

					i=0; j=0; findOldReversingCharLocR=-1; findOldReversingCharLocC=-1;
					while(rNum+i <MAX && cNum-j >= 0 
							&& board[rNum+i][cNum-j] != reversingChar
							&& board[rNum+i][cNum-j] != emptyChar)
					{
						i++;
						j++;
					}
					if (rNum+i < MAX && cNum-j >= 0
						&& board[rNum+i][cNum-j] == reversingChar)
					{
						findOldReversingCharLocR = rNum + i;
						findOldReversingCharLocC = cNum - j;
					}
					i=j=0;
					while(rNum+i < MAX && cNum-j >= 0
							  && rNum+i < findOldReversingCharLocR
							  && cNum-j > findOldReversingCharLocC
							  && findOldReversingCharLocR !=-1 
							  && findOldReversingCharLocC !=-1)
							
					{
						board[rNum+i][cNum-j] = reversingChar;
						i++;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;

				case RIGHTDIAGUP:
					
					i = 0; j = 0; findOldReversingCharLocR=-1; findOldReversingCharLocC=-1;
					while(rNum-i >= 0 && cNum+j < MAX 
						  && board[rNum-i][cNum+j] != reversingChar
						  && board[rNum-i][cNum+j] != emptyChar)
					{
						i++;
						j++;
					}
					if (rNum-i >= 0 && cNum+j < MAX
						&& board[rNum-i][cNum+j] == reversingChar)
					{
						findOldReversingCharLocR = rNum - i;
						findOldReversingCharLocC = cNum + j;
					}
					i=j=0;
					while(rNum-i >= 0 && cNum+j < MAX
						  && rNum-i > findOldReversingCharLocR
						  && cNum+j < findOldReversingCharLocC
						  && findOldReversingCharLocR != -1
						  && findOldReversingCharLocC != -1)
					{
						board[rNum-i][cNum+j] = reversingChar;
						i++;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;
					
				case LEFTDIAGUP:
					
					i=0; j=0; findOldReversingCharLocR=-1; findOldReversingCharLocC=-1;
					while(rNum-i >= 0 && cNum-j >=0 
							&& board[rNum-i][cNum-j] != reversingChar
							&& board[rNum-i][cNum-j] != emptyChar)
					{
						i++;
						j++;
					}
					if (rNum-i >= 0 && cNum-j >=0
						&& board[rNum-i][cNum-j] == reversingChar)
					{
						findOldReversingCharLocR = rNum - i;
						findOldReversingCharLocC = cNum - j;					
					}
					i=j=0;
					while(rNum-i >= 0 && cNum-j >= 0
							  && rNum-i > findOldReversingCharLocR
							  && cNum-j > findOldReversingCharLocC
							  && findOldReversingCharLocR !=-1 
							  && findOldReversingCharLocC !=-1)
					{
						board[rNum-i][cNum-j] = reversingChar;
						i++;
						j++;
						count=(reversingChar==X)?countX++:countO++;
						count=(reversingChar==X)?countO--:countX--;
					}
					break;			

				default: break;	
			} //case
		}
		
		if (countX+countO == MAX_MOVES
				|| (countX == 0 && countO > 0)
				|| (countO == 0 && countX > 0))
		{
			Othello.PlayON = false;
		}
		
	}
	
	/**
	 * Count of the 'X'player's disc.
	 *  
	 * @return the count of X discs
	 */
	public int getCountX()
	{
		return countX;
	}
	/**
	 * Count of the 'O' player's disc.
	 * @return the count of O discs
	 */
	public int getCountO()
	{
		return countO;
	}
	
	/**
	 * Returns whether the current move placement was valid or not.
	 * i.e. the current player could capture the opposite player's discs
	 * in straight line or not.
	 *   
	 * @return true if the placement of the current player's disc was right. 
	 */
	public boolean isPlacementValid()
	{
		return isPlacementValid;
	}
	
	/**
	 * Setter for the Placement validity to reset from the program.
	 * 
	 * @param bool true or false.
	 */
	public void setPlacementValid(boolean bool)
	{
		isPlacementValid = bool;
	}

}
