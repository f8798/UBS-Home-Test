package con.jyh.othello;

import com.jyh.othello.domain.OthelloBoard;
import com.jyh.othello.tools.PositionLoaderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testBoardLoad()
    {
//        OthelloBoard board = new OthelloBoard(8);
//        
//        board.load(PositionLoaderFactory.getCsvPositionLoader());
//        
//        board.printBoard();
    }
}
