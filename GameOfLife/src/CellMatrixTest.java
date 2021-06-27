import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CellMatrixTest {

	@Before
	public void setUp() throws Exception {
	}
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
	CellMatrix x=new CellMatrix(10,10,200,200,a);
	
	@Test
	public void testCellMatrix() {	
		assertEquals(10,x.getHeight());
		assertEquals(10,x.getWidth());
		assertEquals(200,x.getDuration());
		assertEquals(200,x.getTransfromNum());
		for(int m=0;m<10;m++) {
			for(int n=0;n<10;n++) {
			assertEquals(a[m][n],x.getMatrixvalue(m, n, a));
			}
		}
	}

	
	
	@Test
	public void testTransform() {
		//初始化矩阵按规则变化后矩阵
		int [][]b= {
				{0,0,1,0,1,0,0,0,1,0},
				{0,0,0,1,0,0,1,1,0,0},
				{0,1,0,1,0,1,0,1,0,0},
				{0,1,0,0,0,1,0,0,0,0},
				{1,1,0,1,0,1,0,0,0,1},
				{0,0,0,0,0,0,0,1,0,0},
				{0,1,0,1,0,1,0,1,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,1},
				{0,1,1,1,1,1,1,1,1,0}
		};
		x.setMatrix(a);
	int[][]c=x.transform();
	for(int m=0;m<10;m++) {
		for(int n=0;n<10;n++) {
		assertEquals(b[m][n],x.getMatrixvalue(m, n, c));
		}
	}
		
	}
	

	
	
	@Test
	public void testFindLifedNum() {
		int z=x.findLifedNum(0, 0);
		int v=x.findLifedNum(0, 2);
		assertEquals(2,z);
		assertEquals(3,v);
		
	}

}
