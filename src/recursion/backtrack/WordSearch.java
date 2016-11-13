package recursion.backtrack;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
*/

public class WordSearch 
{
    public boolean exist( char[][] board, String word )
    {    	
    	int height = board.length;
    	int width = board[0].length;
    	boolean[][] isVisited = new boolean[height][width];
    	for ( int i = 0; i < board.length; i++ )
    	{
    		for ( int j = 0; j < board[0].length; j++ )
    		{
    			if ( dfs( board, word, isVisited, i, j, 0 ) )
    			{
    				return true;
    			}
    		}
    	}
    	return false;
    }
    
    private boolean dfs( char[][] board, String word, boolean[][] isVisited, int xCoor, int yCoor, int index )
    {
    	if ( index == word.length() )
    	{
    		return true;
    	}

    	if ( xCoor >= board.length 
    			|| yCoor >= board[0].length
    			|| xCoor < 0
    			|| yCoor < 0
    			|| isVisited[xCoor][yCoor] 
    			|| word.charAt( index ) != board[xCoor][yCoor] )
    	{
    		return false;
    	}

    	isVisited[xCoor][yCoor] = true;
    	boolean result= dfs( board, word, isVisited, xCoor + 1, yCoor, index + 1 )
    			|| dfs( board, word, isVisited, xCoor - 1, yCoor, index + 1 )
    			|| dfs( board, word, isVisited, xCoor, yCoor - 1, index + 1 )
    			|| dfs( board, word, isVisited, xCoor, yCoor + 1, index + 1 );
    	isVisited[xCoor][yCoor] = false;
    	return result;
    }
        
    @Test
    public void test()
    {
    	char[][] board = new char[][]{ {'A', 'B', 'C', 'E'},
    								   {'S', 'F', 'C', 'S'},
    								   {'A', 'D', 'E', 'E'} };
    	assertTrue( exist( board, "ABCCED" ) );
    	
    	char[][] board2 = new char[][]{ {'a'} };
    	assertTrue( exist( board2, "a" ) );
    }
}