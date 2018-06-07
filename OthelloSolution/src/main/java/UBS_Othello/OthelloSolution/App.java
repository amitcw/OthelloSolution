package UBS_Othello.OthelloSolution;

import java.util.Scanner;
/**
 * Othello Implementation 
 * 
 * Driving Class for Othello Game.
 * 
 * @author: Amit Wakade
 *
 */
public class App 
{
	public enum players { X, O};
	private static int invalidMoveCountX = 0, invalidMoveCountO = 0;
	private static App.players currentPlayer = players.X;
	private static boolean switchPlayer = false;
	
    public static void main( String[] args )
    {
        
       Scanner sc = new Scanner(System.in);
       App othelloApp = new App();
       Othello reversi = new Othello();
       
       reversi.printBoard();
               
       currentPlayer = players.X;
       
                
       while(Othello.PlayON)
       {
    	   othelloApp.readInput(sc, reversi, currentPlayer);
    	   reversi.printBoard();
	   		if (!switchPlayer && currentPlayer == App.players.X)
				currentPlayer = App.players.O;
			else if (!switchPlayer && currentPlayer == App.players.O)
				currentPlayer = App.players.X;
	   		
	   		if (reversi.isPlacementValid())
	   		{
	   			switchPlayer = false;
	   			invalidMoveCountX = 0;
	   			invalidMoveCountO = 0;
	   		}
	   		else if( invalidMoveCountX == 1 && invalidMoveCountO == 1)
	   		{
	   			Othello.PlayON = false;
	   		}
       }
      
       othelloApp.concludeGame(reversi);
           
       sc.close();
    }
    
    /**
     * This method reads the input from the console the row+column or column+row
     * Assumption: The input will be of 2 characters 1 alfabet from a to h and another 
     * digit from 1 to 8 
     * 
     * @param sc Scanner to read the input
     * @param oth Othello Class Object
     * @param currentPlayer Current Player 'X' or 'O'.
     */
    private void readInput(Scanner sc, Othello oth, App.players currentPlayer)
    {
    	System.out.print("Player \'"+ currentPlayer.toString() +"\' Move:  ");
    	String coOrdinates = sc.next();
    	   	
    	if (coOrdinates.length() == 2)
    	{
    		String coOrds = convertToIndices(coOrdinates);
    		if (oth.validateInput(Character.getNumericValue(coOrds.charAt(0)),
    				              Character.getNumericValue(coOrds.charAt(1)),
    				              currentPlayer))
    		{
	    		oth.play(coOrds, currentPlayer);	    		
    		}
    		else
    		{
    			
    			if (!oth.isPlacementValid() && currentPlayer == App.players.X)	
        		{
        				currentPlayer = App.players.O;
            			switchPlayer = true;
            			invalidMoveCountX++;
        		}
    			else if (!oth.isPlacementValid() && currentPlayer == App.players.O)
    			{
        				currentPlayer = App.players.X;
            			switchPlayer = true;
            			invalidMoveCountO++;
    			}
    				
    			if (invalidMoveCountO == 1 && invalidMoveCountX ==1)
    			{
    				Othello.PlayON = false;
    				System.out.println("Both Players played an Invalid Move.\n");
    				return;
    			}
    			
    			if (!switchPlayer)
    				System.out.println("Invalid Move. Please try again.\n");
    			else
    				System.out.println("Invalid Move. Play passes to other player.\n");
    			
    			readInput(sc, oth, currentPlayer);
    		}  
    		
    	}
    	else
    	{		
    		System.out.println("Invalid Input. please try again.");
    		readInput(sc, oth, currentPlayer);    		
    	}    	
   		
    }
    /**
     * Converter from input string co-ordinates to Othello board matrix indices.
     *  
     * @param st Input String
     * @return the board matrix indices in string.
     */
    private String convertToIndices(String st)
    {
    	//3d or d3 converts in 3,2 x,y co-ordinates indices
    	String string = st.toLowerCase();
    	String coOrds = new String();
    	
    	char cNum;
    	int r = 0; 
    	int c = 0;
    	    	    	
    	if (Character.isDigit(string.charAt(0)))
    	{
    		cNum = string.charAt(1);
    		r = Character.getNumericValue(string.charAt(0)) - 1;
    	}
    	else
    	{
    		cNum = string.charAt(0);
    		r = Character.getNumericValue(string.charAt(1)) - 1;
    	}
    	switch(cNum)
    	{
    	  case 'a' : c=0;
    	             break;
    	  case 'b' : c=1;
    	             break;
    	  case 'c' : c=2;
          			 break;
    	  case 'd' : c=3;
			 		 break;
    	  case 'e' : c=4;
	 		         break;
    	  case 'f' : c=5;
    	             break;
    	  case 'g' : c=6;
	         		 break;
    	  case 'h' : c=7;
  		             break;
	     default  : c=-1;
	    	        break;
    	}
    	coOrds += ""+r+""+c;
    	
    	return coOrds;
    }
    
    /**
     * The ultimate conclusion of the game.
     * 
     * @param oth Othello Class Object.
     */
    public void concludeGame(Othello oth)
    {
		System.out.println("No further moves available");
		if (oth.getCountO() > oth.getCountX())
		{
			System.out.println("Player \'O\' Wins  (" + oth.getCountO() +" vs "+ oth.getCountX() +" )");
		}
		else if (oth.getCountO() < oth.getCountX())
		{
			System.out.println("Player \'X\' Wins  (" + oth.getCountX() +" vs "+ oth.getCountO() +" )");
		}
		else 
		{
			System.out.println("Game is a Draw between both \'X\' and \'O\'  (" + oth.getCountX() +" vs "+ oth.getCountO() +" )");
		}
		
		Othello.PlayON = false;
    }
}
