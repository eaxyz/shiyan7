import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;



public class GameOfLifeFrameTest {

	@Before
	public void setUp() throws Exception {
	}
	GameOfLifeFrame G=new GameOfLifeFrame();
	
	String filepath = "C:\\Users\\ам\\Desktop\\GameOfLife\\case_0.txt";
    CellMatrix C = Tool.initMatrixFromFile(filepath);  
    
	
	@Test
	public void testGameControlTask() 
	{
		int num=G.getnum();
        while (!false&&G.getnum()!=0) {
            C.transform();
            try {
                TimeUnit.MILLISECONDS.sleep(G.getduration());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            num--;
   
        }
	}
	
} 
