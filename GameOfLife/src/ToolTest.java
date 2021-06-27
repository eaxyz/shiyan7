import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ToolTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testInitMatrixFromFile() {
		//ͨ�����෽������Դ�ļ���ȡ����
            String filepath = "C:\\Users\\��\\Desktop\\GameOfLife\\case_0.txt";
            CellMatrix C = Tool.initMatrixFromFile(filepath);  
            //��ʵ�������ж��Ƿ����
		     assertEquals(C.getHeight(),10);
		     assertEquals(C.getWidth(),10);
		     assertEquals(C.getDuration(),200);
		     assertEquals(C.getTransfromNum(),200);
		     int[][]a = {
		 			{0,1,0,1,1,0,0,1,1,0},
		 		    {0,1,0,0,0,1,0,0,0,1},
		 			{0,0,0,1,0,0,0,1,0,0},
		 			{1,0,1,1,1,0,1,1,1,0},
		 			{0,1,0,1,0,1,0,1,1,0},
		 			{0,1,0,0,0,1,0,0,0,1},
		 			{0,0,0,1,0,0,0,1,0,0},
		 			{1,0,1,1,1,0,1,1,1,0},
		 			{1,0,1,1,1,0,1,1,1,0},
		 			{0,1,0,0,0,1,0,0,0,1}
		 			                               };
		     for(int m=0;m<10;m++) {
					for(int n=0;n<10;n++) {
					assertEquals(a[m][n],C.getMatrixvalue(m, n, a));
					}
				}
	
	}

}
